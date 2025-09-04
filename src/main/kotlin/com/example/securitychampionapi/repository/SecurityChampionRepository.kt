package com.example.securitychampionapi.service

import com.example.securitychampionapi.dto.SecurityChampion
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet


@Repository
class SecurityChampionRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {


    fun getSecurityChampions(repositories: List<String>): List<SecurityChampion> {
        val query = "SELECT email, reponame FROM securitychampions " +
                "JOIN repositories ON securitychampions.id = repositories.secchampid " +
                "WHERE reponame IN (:repositories)"

        val params = MapSqlParameterSource()
        params.addValue("repositories", repositories)

        val result = jdbcTemplate.query(
            query,
            params,
            SecurityChampionRowMapper()
        ).toList()

        return result
    }

    class SecurityChampionRowMapper : RowMapper<SecurityChampion> {
        override fun mapRow(rs: ResultSet, rowNum: Int): SecurityChampion {
            return SecurityChampion(
                repositoryName = rs.getString("repoName"),
                securityChampionEmail = rs.getString("email"),
            )
        }
    }

}
