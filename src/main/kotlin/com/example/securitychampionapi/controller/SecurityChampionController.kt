package com.example.securitychampionapi.controller

import com.example.securitychampionapi.controller.models.GetSecurityChampionsBody
import com.example.securitychampionapi.controller.models.GetSecurityChampionsResponse
import com.example.securitychampionapi.controller.models.SecurityChampionResponse
import com.example.securitychampionapi.controller.models.SetSecurityChampionBody
import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.controller.models.SetSecurityChampionResponse
import com.example.securitychampionapi.controller.models.SetSecurityChampionWithoutRepoBody
import com.example.securitychampionapi.service.SecurityChampionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class SecurityChampionController(val securityChampionService: SecurityChampionService) {

    @PostMapping("/securityChampion")
    fun getSecurityChampions(@RequestBody body: GetSecurityChampionsBody): GetSecurityChampionsResponse {
        return securityChampionService.getSecurityChampions(body.repositoryNames)
                .map { SecurityChampionResponse(
                    repositoryName = it.repositoryName,
                    securityChampionEmail = it.securityChampionEmail
                )
            }

    }

    @PostMapping("/setSecurityChampion")
    fun setSecurityChampion(@RequestBody body: SetSecurityChampionBody): SetSecurityChampionResponse {
        securityChampionService.setSecurityChampion(
            repositoryName = body.repositoryName,
            securityChampionEmail = body.securityChampionEmail
        )
        return SetSecurityChampionResponse(statusMessage = "SUCCESS")
    }
    @PostMapping("/setSecurityChampionWithoutRepo")
    fun setSecurityChampionWithoutRepo(@RequestBody body: SetSecurityChampionWithoutRepoBody): SetSecurityChampionResponse {
        securityChampionService.setSecurityChampionWithoutRepo(
            securityChampionEmail = body.securityChampionEmail
        )
        return SetSecurityChampionResponse(statusMessage = "SUCCESS")
    }

    @GetMapping("/getAllSecurityChampions")
    fun getAllSecurityChampions(): ResponseEntity<List<String>> {
        return try {
            ResponseEntity<List<String>>(
                securityChampionService.getAllSecurityChampions(),
                HttpStatus.OK,
            )
        } catch (error: ResponseStatusException) {
            ResponseEntity.status(error.statusCode).body(emptyList())
        }
    }
}