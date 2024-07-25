package mek.backend.iam.entity

import mek.backend.auth.model.enums.UserStatus
import java.util.UUID

data class IamUserEntity(val id: UUID, val email: String, val firstName: String, val lastName: String, val status: UserStatus)