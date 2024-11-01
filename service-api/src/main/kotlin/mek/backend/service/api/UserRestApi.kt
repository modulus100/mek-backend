package mek.backend.service.api

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import mek.backend.iam.service.IamUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "Authentication")
class UserRestApi(
    val userService: IamUserService,
) {

//    @PreAuthorize("hasRole('ADMIN') and hasAnyAuthority('admin:create')")
    @GetMapping("/")
    fun getAllUsers(): GetAllUsersResponse {
        return GetAllUsersResponse(
            users = userService.getAllUsers().map(User::fromEntity)
        )
    }
}