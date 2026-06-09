package com.example.securitychampionapi.observability

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.UUID
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class HttpAccessLoggingFilter : OncePerRequestFilter() {
    override fun shouldNotFilter(request: HttpServletRequest): Boolean = request.requestURI == "/actuator/prometheus"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val startedAt = System.nanoTime()
        val requestId = request.getHeader(REQUEST_ID_HEADER)?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val clientIp = resolveClientIp(request)

        MDC.put("requestId", requestId)
        response.setHeader(REQUEST_ID_HEADER, requestId)

        try {
            filterChain.doFilter(request, response)
        } finally {
            val durationMs = (System.nanoTime() - startedAt) / 1_000_000
            MDC.put("event", "http_access")
            MDC.put("httpMethod", request.method)
            MDC.put("path", request.requestURI)
            MDC.put("status", response.status.toString())
            MDC.put("durationMs", durationMs.toString())
            MDC.put("clientIp", clientIp)
            log.info("http_access")
            MDC.remove("event")
            MDC.remove("httpMethod")
            MDC.remove("path")
            MDC.remove("status")
            MDC.remove("durationMs")
            MDC.remove("clientIp")
            MDC.remove("requestId")
        }
    }

    private fun resolveClientIp(request: HttpServletRequest): String {
        val forwardedFor = request.getHeader("X-Forwarded-For")
        if (!forwardedFor.isNullOrBlank()) {
            return forwardedFor.split(',').first().trim()
        }
        return request.remoteAddr ?: "unknown"
    }

    companion object {
        private val log = LoggerFactory.getLogger(HttpAccessLoggingFilter::class.java)
        private const val REQUEST_ID_HEADER = "X-Request-Id"
    }
}
