package com.example.securitychampionapi.service

import com.example.securitychampionapi.controller.models.SecurityChampionResponse
import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.repository.SecurityChampionRepository
import org.springframework.stereotype.Service


@Service
class SecurityChampionService(private val repository: SecurityChampionRepository) {


    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> =
        repository.getSecurityChampions(repositories)


    fun setSecurityChampion(repositoryName: String, securityChampionEmail: String, modifiedBy: String) =
        repository.setSecurityChampion(repositoryName, securityChampionEmail, modifiedBy)

    fun getAllRepositoryNamesWithSecurityChampion() = repository.getRepositoriesWithSecurityChampions().map { SecurityChampionResponse(it.repositoryName, it.securityChampionEmail) }
}
