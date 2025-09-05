package com.example.securitychampionapi.controller

import com.example.securitychampionapi.controller.models.GetSecurityChampionsBody
import com.example.securitychampionapi.controller.models.GetSecurityChampionsResponse
import com.example.securitychampionapi.controller.models.SecurityChampionResponse
import com.example.securitychampionapi.controller.models.SetSecurityChampionBody
import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.controller.models.SetSecurityChampionResponse
import com.example.securitychampionapi.service.SecurityChampionService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
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
}