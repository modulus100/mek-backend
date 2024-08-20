package mek.backend.service.api

import io.swagger.v3.oas.annotations.media.Schema
import mek.backend.auth.model.enums.UserStatus
import mek.backend.iam.entity.IamUserEntity
import java.time.Instant
import java.util.*

data class GetAllUsersResponse(
    @field:Schema(required = true)
    val users: List<User>
)

data class User(
    @field:Schema(required = true)
    val id: UUID,

    @field:Schema(required = true)
    val email: String,

    @field:Schema(required = true)
    val firstName: String,

    @field:Schema(required = true)
    val lastName: String,

    @field:Schema(required = true)
    val status: UserStatus,

    @field:Schema(required = true)
    val registrationDate: Instant,

    @field:Schema(required = true)
    val birthDate: Instant,

    @field:Schema(required = true, name = "isActive", type = "boolean")
    val isActive: Boolean,

    val jobTitle: String?,
    val phoneNumber: String?,
    val personalIdCode: String?
) {
    companion object {
        fun fromEntity(userEntity: IamUserEntity): User = User(
            userEntity.id,
            userEntity.email,
            userEntity.firstName,
            userEntity.lastName,
            userEntity.status,
            userEntity.registrationDate,
            userEntity.birthDate,
            userEntity.isActive,
            userEntity.jobTitle,
            userEntity.phoneNumber,
            userEntity.personalIdCode
        )
    }
}