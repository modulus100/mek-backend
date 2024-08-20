package mek.backend.iam.repository

import mek.backend.auth.model.enums.UserStatus
import mek.backend.iam.entity.IamUserEntity
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class IamUserRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) {

//    fun getByEmail(email: String): IamUserEntity {
//        val user = jdbcTemplate.queryForObject("""
//            select * from user_account where email = :email;
//        """.trimIndent(),
//            mapOf("email" to email)
//        ) { rs, _ ->
//            IamUserEntity(
//                id = rs.getObject("id", UUID::class.java),
//                email = rs.getString("email"),
//                firstName = rs.getString("first_name"),
//                lastName = rs.getString("last_name"),
//                status = UserStatus.valueOf(rs.getString("status"))
//            )
//        }
//
//        if (user == null) {
//            throw Exception("user not found by email")
//        }
//
//        return user
//    }

    fun getAllUsers(): List<IamUserEntity> {
        return jdbcTemplate.query(
            """
            select
                id,
                email,
                password,
                first_name,
                last_name,
                status,
                registration_date,
                birth_date,
                is_active,
                job_title,
                phone_number,
                personal_id_code
            from user_account;
        """.trimIndent()
        ) { rs, _ ->
            IamUserEntity(
                id = rs.getObject("id", UUID::class.java),
                email = rs.getString("email"),
                firstName = rs.getString("first_name"),
                lastName = rs.getString("last_name"),
                status = UserStatus.valueOf(rs.getString("status")),
                registrationDate = rs.getTimestamp("registration_date").toInstant(),
                birthDate = rs.getTimestamp("birth_date").toInstant(),
                isActive = rs.getBoolean("phone_number"),
                jobTitle = rs.getString("job_title"),
                phoneNumber = rs.getString("phone_number"),
                personalIdCode = rs.getString("personal_id_code")
            )
        }
    }
}