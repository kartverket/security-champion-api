package com.example.securitychampionapi.service

import com.example.securitychampionapi.dto.SecurityChampion
import com.example.securitychampionapi.repository.SecurityChampionRepository
import org.springframework.stereotype.Service


@Service
class SecurityChampionService(val securityChampionRepository: SecurityChampionRepository) {


    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> =
              emptyList()



}
