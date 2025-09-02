package com.example.securitychampionapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class SecurityChampionController {
    @GetMapping("/api/securityChampion/")
    fun listMessages() = listOf(
        SecurityChampion("1", "aleksanderobrestad@skkrt.com!", "TestRepo"),
        SecurityChampion("2", "erik@skkrt.com!", "testrepo2")
    )
}