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
                "JOIN repositories ON securitychampions.email = repositories.secchampemail " +
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

        //Inserting secchamp if it does not already exist
        val query1 = "INSERT INTO securitychampions (email)" +
                "VALUES (:email)" +
                "ON CONFLICT (email) DO NOTHING;"
        val secChampParams = MapSqlParameterSource()
        secChampParams.addValue("email", securityChampion.securityChampionEmail)

        jdbcTemplate.update(
            query1,
            secChampParams
        )

        val query2 = "INSERT INTO repositories (reponame, secchampemail)" +
                "VALUES (:repo, :email)" +
                "ON CONFLICT (reponame) DO UPDATE " +
                "SET secchampemail = (:email);"

        val repoParams = MapSqlParameterSource()
        repoParams.addValue("email", securityChampion.securityChampionEmail)
        repoParams.addValue("repo", securityChampion.repositoryName)

        jdbcTemplate.update(
            query2,
            repoParams
        )
        return "Updated SecurityChampion"
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

