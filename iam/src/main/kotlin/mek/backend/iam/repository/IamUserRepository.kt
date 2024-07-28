package mek.backend.iam.repository

import mek.backend.auth.model.enums.UserStatus
import mek.backend.iam.entity.IamUserEntity
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class IamUserRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) {

    fun getByEmail(email: String): IamUserEntity {
        val user = jdbcTemplate.queryForObject("""
            select * from user_account where email = :email;
        """.trimIndent(),
            mapOf("email" to email), RowMapper<IamUserEntity>
            { rs, _ ->
                IamUserEntity(
                    id = rs.getObject("id", UUID::class.java),
                    email = rs.getString("email"),
                    firstName = rs.getString("first_name"),
                    lastName = rs.getString("last_name"),
                    status = UserStatus.valueOf(rs.getString("status"))
                )
            })

        if (user == null) {
            throw Exception("user not found by email")
        }

        return user
    }
}