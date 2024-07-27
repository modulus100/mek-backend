package mek.backend.service.api

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import mek.backend.iam.repository.IamUserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "Authentication")
class UserRestApi(
    val userRepository: IamUserRepository,
) {

    data class Player(
        @field:Schema(required = true)
        val id: UUID,
        @field:Schema(required = true)
        val name: String
    )

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN') and hasAnyAuthority('admin:create')")
    fun getPlayer(): Player {
        val user = userRepository.getByEmail("aleksandr.ts@gmail.com")
        return Player(user.id, user.lastName)
    }
}