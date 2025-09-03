package com.example.securitychampionapi.repository

import com.example.securitychampionapi.dto.SecurityChampion
import org.springframework.stereotype.Repository

@Repository
class SecurityChampionRepository() {

    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> =
        repositories.map { repoName ->
            SecurityChampion(repositoryName = repoName, securityChampionEmail = "$repoName@mail.com")
        }

}
