package com.example.securitychampionapi.service

import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.repository.SecurityChampionRepository
import org.springframework.stereotype.Service

@Service
class SecurityChampionService(
    private val repository: SecurityChampionRepository,
) {
    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> = repository.getSecurityChampions(repositories)

    fun setSecurityChampion(
        repositoryName: String,
        securityChampionEmail: String,
        modifiedBy: String,
    ) = repository.setSecurityChampion(repositoryName, securityChampionEmail, modifiedBy)

    fun setSecurityChampions(
        repositoryNames: List<String>,
        securityChampionEmail: String,
        modifiedBy: String,
    ): IntArray = repository.setSecurityChampions(repositoryNames, securityChampionEmail, modifiedBy)

    fun getAllRepositoryNamesWithSecurityChampion(): List<SecurityChampion> = repository.getRepositoriesWithSecurityChampions()

    fun setSecurityChampionWithNoRepo(
        securityChampionEmail: String,
        modifiedBy: String,
    ) {
        repository.setSecurityChampionWithNoRepo(securityChampionEmail, modifiedBy)
    }
}
