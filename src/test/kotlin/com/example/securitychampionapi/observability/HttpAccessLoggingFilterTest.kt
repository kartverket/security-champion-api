package com.example.securitychampionapi.observability

import jakarta.servlet.FilterChain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

class HttpAccessLoggingFilterTest {
    private val filter = HttpAccessLoggingFilter()

    @Test
    fun `adds request id header when missing`() {
        val request = MockHttpServletRequest("GET", "/api/securityChampions")
        val response = MockHttpServletResponse()

        filter.doFilter(request, response, FilterChain { _, _ -> })

        val responseRequestId = response.getHeader("X-Request-Id")
        assertNotNull(responseRequestId)
        assertEquals(36, responseRequestId!!.length)
    }

    @Test
    fun `reuses incoming request id`() {
        val incomingRequestId = "req-123"
        val request = MockHttpServletRequest("GET", "/api/securityChampions")
        request.addHeader("X-Request-Id", incomingRequestId)
        val response = MockHttpServletResponse()

        filter.doFilter(request, response, FilterChain { _, _ -> })

        assertEquals(incomingRequestId, response.getHeader("X-Request-Id"))
    }
}
