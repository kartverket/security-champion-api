package com.example.securitychampionapi.repository

import com.example.securitychampionapi.repository.models.SecurityChampionDB
import org.springframework.data.repository.CrudRepository


interface SecurityChampionRepository : CrudRepository<SecurityChampionDB, String>
