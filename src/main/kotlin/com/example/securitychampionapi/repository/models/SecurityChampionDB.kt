package com.example.securitychampionapi.repository.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("securityChampions")
data class SecurityChampionDB (
    @Id val id: String? = null,
    val email: String? = null,

    )