package mek.backend.service.api

import jakarta.validation.Valid
import mek.backend.auth.model.dto.request.LoginRequest
import mek.backend.auth.model.dto.request.RegisterRequest
import mek.backend.auth.model.dto.request.TokenInvalidateRequest
import mek.backend.auth.model.dto.request.TokenRefreshRequest
import mek.backend.auth.model.dto.response.LoginResponse
import mek.backend.auth.model.entity.UserEntity
import mek.backend.auth.service.LoginService
import mek.backend.auth.service.LogoutService
import mek.backend.auth.service.RefreshTokenService
import mek.backend.auth.service.RegisterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AuthRestApi(
    val registerService: RegisterService,
    val loginService: LoginService,
    val refreshTokenService: RefreshTokenService,
    val logoutService: LogoutService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@Valid @RequestBody registerRequest: RegisterRequest): ResponseEntity<Unit> {
        registerService.registerUser(registerRequest)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: @Valid LoginRequest): LoginResponse {
        return loginService.login(loginRequest)
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody tokenRefreshRequest: @Valid TokenRefreshRequest): LoginResponse {
        return refreshTokenService.refreshToken(tokenRefreshRequest)
    }

    @PostMapping("/logout")
    fun logout(@RequestBody tokenInvalidateRequest: @Valid TokenInvalidateRequest) {
        logoutService.logout(tokenInvalidateRequest)
    }
}