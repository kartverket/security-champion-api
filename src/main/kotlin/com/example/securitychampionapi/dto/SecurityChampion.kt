package com.example.securitychampionapi.dto


data class SecurityChampion(
    val repository: String,
    val email: String,
    val modifiedBy: String? = null,
)