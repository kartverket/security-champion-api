package com.example.securitychampionapi.controller.models

data class SecurityChampionWithNoRepoBody(
    val securityChampionEmail: String,
    val modifiedBy: String = "No user provided",
)
