package mek.backend.service.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import mek.backend.auth.model.dto.request.LoginRequest
import mek.backend.auth.model.dto.request.RegisterRequest
import mek.backend.auth.model.dto.request.TokenInvalidateRequest
import mek.backend.auth.model.dto.request.TokenRefreshRequest
import mek.backend.auth.model.dto.response.LoginResponse
import mek.backend.auth.service.LoginService
import mek.backend.auth.service.LogoutService
import mek.backend.auth.service.RefreshTokenService
import mek.backend.auth.service.RegisterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Authentication", description = "Authentication related endpoints")
@RequestMapping("/auth")
class AuthRestApi(
    val registerService: RegisterService,
    val loginService: LoginService,
    val refreshTokenService: RefreshTokenService,
    val logoutService: LogoutService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "User registered successfully",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "409", description = "Email already exists")
        ]
    )
    @RequestBody(content = [Content(schema = Schema(implementation = RegisterRequest::class))], required = true)
    fun registerUser(
        @Valid
        @org.springframework.web.bind.annotation.RequestBody
        registerRequest: RegisterRequest
    ): ResponseEntity<Unit> {
        registerService.registerUser(registerRequest)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/login")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User logged out successfully",
                content = [Content(schema = Schema(implementation = LoginResponse::class))]
            )
        ]
    )
    @RequestBody(content = [Content(schema = Schema(implementation = LoginRequest::class))], required = true)
    fun login(
        @Valid
        @org.springframework.web.bind.annotation.RequestBody
        loginRequest: LoginRequest
    ): LoginResponse {
        return loginService.login(loginRequest)
    }

    @PostMapping("/refresh-token")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User logged in successfully",
                content = [Content(schema = Schema(implementation = LoginResponse::class))]
            )
        ]
    )
    @RequestBody(content = [Content(schema = Schema(implementation = TokenRefreshRequest::class))], required = true)
    fun refreshToken(
        @Valid
        @org.springframework.web.bind.annotation.RequestBody
        tokenRefreshRequest: TokenRefreshRequest
    ): LoginResponse {
        return refreshTokenService.refreshToken(tokenRefreshRequest)
    }

    @PostMapping("/logout")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Token invalidated successfully",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ]
    )
    @RequestBody(content = [Content(schema = Schema(implementation = TokenInvalidateRequest::class))], required = true)
    fun logout(
        @Valid
        @org.springframework.web.bind.annotation.RequestBody
        tokenInvalidateRequest: TokenInvalidateRequest
    ) {
        logoutService.logout(tokenInvalidateRequest)
    }
}