package mek.backend.service.api

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import mek.backend.iam.service.IamUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "Authentication")
class UserRestApi(
    val userService: IamUserService,
) {

//    data class Player(
//        @field:Schema(required = true)
//        val id: UUID,
//        @field:Schema(required = true)
//        val name: String
//    )

//    @GetMapping("/test")
//    @PreAuthorize("hasRole('ADMIN') and hasAnyAuthority('admin:create')")
//    fun getPlayer(): Player {
//        return Player(UUID.randomUUID(), "kek")
//    }

    @GetMapping("/")
    fun getAllUsers(): GetAllUsersResponse {
        return GetAllUsersResponse(
            users = userService.getAllUsers().map(User::fromEntity)
        )
    }
}