package com.example.securitychampionapi.controller

import com.example.securitychampionapi.controller.models.SecurityChampionResponse
import com.example.securitychampionapi.service.SecurityChampionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SmapiController(
    val securityChampionService: SecurityChampionService,
) {
    @GetMapping("/repositories/all")
    fun getAllRepositoryNamesWithSecurityChampion(): List<SecurityChampionResponse> =
        securityChampionService
            .getAllRepositoryNamesWithSecurityChampion()
            .map {
                SecurityChampionResponse(
                    repositoryName = it.repository,
                    securityChampionEmail = it.email,
                )
            }
}
