package com.example.securitychampionapi.controller.models


typealias GetSecurityChampionsResponse = List<SecurityChampionResponse>

data class SecurityChampionResponse(
    val repositoryName: String,
    val securityChampionEmail: String,
)

