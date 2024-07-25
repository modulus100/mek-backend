package mek.backend.app.api

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import mek.backend.iam.entity.IamUserEntity
import mek.backend.iam.repository.IamUserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/v1/user")
class UserRestApi(
    val userRepository: IamUserRepository,
) {

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN') and hasAnyAuthority('admin:create')")
    fun getUser(): IamUserEntity {
        val user = userRepository.getByEmail("aleksandr.ts@gmail.com")
        return user
    }
}