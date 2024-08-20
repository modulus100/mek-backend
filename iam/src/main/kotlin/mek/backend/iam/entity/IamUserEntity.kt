package mek.backend.iam.entity

import mek.backend.auth.model.enums.UserStatus
import java.time.Instant
import java.util.UUID

data class IamUserEntity(
    val id: UUID,
    val email: String,
    val firstName: String,
    val lastName: String,
    val status: UserStatus,
    val registrationDate: Instant,
    val birthDate: Instant,
    val isActive: Boolean,
    val jobTitle: String?,
    val phoneNumber: String?,
    val personalIdCode: String?
)