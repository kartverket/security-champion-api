package com.example.securitychampionapi.repository

import com.example.securitychampionapi.dto.SecurityChampion
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet


@Repository
class SecurityChampionRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {


    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> {
        val query = """
            SELECT email, repository FROM securityChampion
             WHERE repository IN (:repositories)
         """

        val params = MapSqlParameterSource()
        params.addValue("repositories", repositories)

        val result = jdbcTemplate.query(
            query,
            params,
            SecurityChampionRowMapper()
        ).toList()

        return result
    }

    fun setSecurityChampion(repositoryName: String, securityChampionEmail: String): Int {
        val query = """    
        INSERT INTO securityChampion (email, repository) 
        VALUES (:email, :repository) 
        ON CONFLICT (repository) 
        DO UPDATE SET email = :email;
        """

        val params = MapSqlParameterSource()
            .addValue("email", securityChampionEmail)
            .addValue("repository", repositoryName)

        return jdbcTemplate.update(
            query,
            params
        )
    }

    fun getRepositoriesWithSecurityChampions(): List<SecurityChampion> {
        val query = """
                SELECT email, repository FROM securityChampion
                WHERE repository IS NOT NULL
        """

        val result = jdbcTemplate.query(
            query,
            SecurityChampionRowMapper()
        ).toList()
        return result
    }

    class SecurityChampionRowMapper : RowMapper<SecurityChampion> {
        override fun mapRow(rs: ResultSet, rowNum: Int): SecurityChampion {
            return SecurityChampion(
                repositoryName = rs.getString("repository"),
                securityChampionEmail = rs.getString("email"),
            )
        }
    }
}

