package com.example.securitychampionapi.controller.models

data class SetSecurityChampionsBody(
    val repositoryNames: List<String>,
    val securityChampionEmail: String,
    val modifiedBy: String = "No user provided"
)