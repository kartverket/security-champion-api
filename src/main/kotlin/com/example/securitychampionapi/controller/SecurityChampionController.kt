package com.example.securitychampionapi.controller

import com.example.securitychampionapi.dto.GetSecurityChampionsBody
import com.example.securitychampionapi.dto.SecurityChampion
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityChampionController {
    @PostMapping("/api/securityChampion/")
    fun getSecurityChampions( @RequestBody body: GetSecurityChampionsBody) {
        listOf(
            SecurityChampion(id = "Test", email = "mail.com", repo = "repo"),
            SecurityChampion(id = "Test", email = "mail.com", repo = "repo"),
            SecurityChampion(id = "Test", email = "mail.com", repo = body.repositoryNames.toString())
        )


    }
}