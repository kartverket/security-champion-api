package com.example.securitychampionapi.service

import com.example.securitychampionapi.dto.SecurityChampion
import org.springframework.stereotype.Service


@Service
class SecurityChampionService(private val repository: SecurityChampionRepository) {


    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> =
        repository.getSecurityChampions(repositories)


    fun getSecurityChampionById(id : String): SecurityChampion  = TODO()
}
