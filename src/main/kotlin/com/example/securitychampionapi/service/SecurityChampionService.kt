package com.example.securitychampionapi.service

import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.repository.SecurityChampionRepository
import org.springframework.stereotype.Service


@Service
class SecurityChampionService(private val repository: SecurityChampionRepository) {


    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> =
        repository.getSecurityChampions(repositories)

    fun getAllSecurityChampions(): List<String> =
        repository.getAllSecurityChampions()

    fun setSecurityChampion(repositoryName: String, securityChampionEmail: String) =
        repository.setSecurityChampion(repositoryName, securityChampionEmail)

    fun setSecurityChampionWithoutRepo(securityChampionEmail: String) =
        repository.setSecurityChampionWithoutRepo(securityChampionEmail)
}
