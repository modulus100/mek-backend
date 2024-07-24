package mek.backend.app.api

import jakarta.validation.Valid
import mek.backend.auth.controller.CustomResponse
import mek.backend.auth.model.Token
import mek.backend.auth.model.dto.request.LoginRequest
import mek.backend.auth.model.dto.request.RegisterRequest
import mek.backend.auth.model.dto.request.TokenInvalidateRequest
import mek.backend.auth.model.dto.request.TokenRefreshRequest
import mek.backend.auth.model.dto.response.TokenResponse
import mek.backend.auth.model.mapper.TokenToTokenResponseMapper
import mek.backend.auth.service.LoginService
import mek.backend.auth.service.LogoutService
import mek.backend.auth.service.RefreshTokenService
import mek.backend.auth.service.RegisterService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val registerService: RegisterService,
    val loginService: LoginService,
    val refreshTokenService: RefreshTokenService,
    val logoutService: LogoutService
) {

    private val tokenToTokenResponseMapper: TokenToTokenResponseMapper = TokenToTokenResponseMapper.initialize()

    /**
     * Registers a new user.
     *
     * @param registerRequest the request object containing registration details.
     * @return a [CustomResponse] indicating success or failure.
     */
    @PostMapping("/register")
    fun registerAdmin(@RequestBody registerRequest: @Valid RegisterRequest): CustomResponse<Void> {
        registerService.registerUser(registerRequest)
        return CustomResponse.SUCCESS
    }

    /**
     * Logs in a user and returns an access token.
     *
     * @param loginRequest the request object containing login credentials.
     * @return a [CustomResponse] containing the [TokenResponse].
     */
    @PostMapping("/login")
    fun loginAdmin(@RequestBody loginRequest: @Valid LoginRequest): CustomResponse<TokenResponse> {
        val token: Token = loginService.login(loginRequest)
        val tokenResponse: TokenResponse = tokenToTokenResponseMapper.map(token)
        return CustomResponse.successOf(tokenResponse)
    }

    /**
     * Refreshes the access token using a refresh token.
     *
     * @param tokenRefreshRequest the request object containing the refresh token.
     * @return a [CustomResponse] containing the new [TokenResponse].
     */
    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody tokenRefreshRequest: @Valid TokenRefreshRequest): CustomResponse<TokenResponse> {
        val token: Token = refreshTokenService.refreshToken(tokenRefreshRequest)
        val tokenResponse: TokenResponse = tokenToTokenResponseMapper.map(token)
        return CustomResponse.successOf(tokenResponse)
    }

    /**
     * Logs out a user by invalidating their token.
     *
     * @param tokenInvalidateRequest the request object containing the token to be invalidated.
     * @return a [CustomResponse] indicating success or failure.
     */
    @PostMapping("/logout")
    fun logout(@RequestBody tokenInvalidateRequest: @Valid TokenInvalidateRequest): CustomResponse<Void> {
        logoutService.logout(tokenInvalidateRequest)
        return CustomResponse.SUCCESS
    }
}
