package com.example.securitychampionapi.controller

import com.example.securitychampionapi.dto.GetSecurityChampionsBody
import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.service.SecurityChampionService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:3000")
class SecurityChampionController(val securityChampionService: SecurityChampionService) {

    @PostMapping("/api/securityChampion/")
    fun getSecurityChampions(@RequestBody body: GetSecurityChampionsBody): List<SecurityChampion> {
       return securityChampionService.getSecurityChampions(body.repositoryNames)
    }
}