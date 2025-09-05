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
        val query = "SELECT email, reponame FROM securityChampion_repositories " +
                "JOIN securityChampions ON securityChampion_repositories.secChampEmail = securityChampions.email " +
                "JOIN repositories ON securityChampion_repositories.repoId = repositories.id " +
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

    fun setSecurityChampion(securityChampion: SecurityChampion): String {
        createNewSecurityChampionIfNotExists(securityChampion.securityChampionEmail)
        createNewRepositoryIfNotExists(securityChampion.repositoryName)
        linkRepositoryToSecurityChampion(securityChampion.repositoryName, securityChampion.securityChampionEmail)
        return "Updated SecurityChampion"
    }

    private fun createNewSecurityChampionIfNotExists(securityChampionEmail: String) : Int {
        val query = "INSERT INTO securitychampions (email) VALUES (:email) ON CONFLICT (email) DO NOTHING;"

        val params = MapSqlParameterSource()
            .addValue("email", securityChampionEmail)

        return jdbcTemplate.update(
            query,
            params
        )

    }
    private fun createNewRepositoryIfNotExists(repositoryName: String) : Int {
        val query = "INSERT INTO repositories (reponame) VALUES (:reponame) ON CONFLICT (reponame) DO NOTHING;"
        val params = MapSqlParameterSource()
            .addValue("reponame", repositoryName)

        return jdbcTemplate.update(
            query,
            params
        )
    }

    private fun linkRepositoryToSecurityChampion(repositoryName: String, securityChampionEmail: String): Int {

        val query = "INSERT INTO securityChampion_repositories (repoId, secChampEmail)" +
                "VALUES ((SELECT id FROM repositories WHERE reponame = :reponame), :email)" +
                "ON CONFLICT (repoId) DO UPDATE SET secChampEmail = :email;"

        val params = MapSqlParameterSource()
            .addValue("reponame", repositoryName)
            .addValue("email", securityChampionEmail)

        return jdbcTemplate.update(
            query,
            params
        )
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

