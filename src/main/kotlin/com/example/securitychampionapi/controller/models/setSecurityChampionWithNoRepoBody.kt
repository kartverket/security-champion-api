package com.example.securitychampionapi.controller.models

data class setSecurityChampionWithNoRepoBody(
    val securityChampionEmail: String,
    val modifiedBy: String = "No user provided"
)

