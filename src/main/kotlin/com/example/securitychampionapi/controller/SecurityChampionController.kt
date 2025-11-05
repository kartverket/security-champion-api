package com.example.securitychampionapi.controller


import com.example.securitychampionapi.controller.models.*
import com.example.securitychampionapi.service.SecurityChampionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SecurityChampionController(val securityChampionService: SecurityChampionService) {

    @PostMapping("/securityChampion")
    fun getSecurityChampions(@RequestBody body: GetSecurityChampionsBody): GetSecurityChampionsResponse {
        return securityChampionService.getSecurityChampions(body.repositoryNames)
            .map {
                SecurityChampionResponse(
                    repositoryName = it.repository,
                    securityChampionEmail = it.email
                )
            }

    }

    @PostMapping("/setSecurityChampion")
    fun setSecurityChampion(@RequestBody body: SetSecurityChampionBody): SetSecurityChampionResponse {
        securityChampionService.setSecurityChampion(
            repositoryName = body.repositoryName,
            securityChampionEmail = body.securityChampionEmail,
            modifiedBy = body.modifiedBy
        )
        return SetSecurityChampionResponse(status = HttpStatus.OK)
    }

    @PostMapping("/setSecurityChampionWithNoRepo")
    fun setSecurityChampionWithNoRepo(@RequestBody body: setSecurityChampionWithNoRepoBody): SetSecurityChampionResponse {
        securityChampionService.setSecurityChampionWithNoRepo(
            securityChampionEmail = body.securityChampionEmail,
            modifiedBy = body.modifiedBy
        )
        return SetSecurityChampionResponse(statusMessage = "SUCCESS")

    }

    @PostMapping("/setSecurityChampions")
    fun setSecurityChampions(@RequestBody body: SetSecurityChampionsBody): SetSecurityChampionResponse {
        securityChampionService.setSecurityChampions(
            repositoryNames = body.repositoryNames,
            securityChampionEmail = body.securityChampionEmail,
            modifiedBy = body.modifiedBy
        )
        return SetSecurityChampionResponse(status = HttpStatus.OK)
    }
}