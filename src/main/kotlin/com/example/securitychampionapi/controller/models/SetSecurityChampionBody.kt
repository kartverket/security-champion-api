package com.example.securitychampionapi.controller.models


data class SetSecurityChampionBody(
    val repositoryName: String,
    val securityChampionEmail: String,
    val modifiedBy: String,
)