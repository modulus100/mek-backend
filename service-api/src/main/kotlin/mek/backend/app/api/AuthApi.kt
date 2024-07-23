package mek.backend.app.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthApi {

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/register")
    fun register(): String {
        return "register"
    }

    @GetMapping("/logout")
    fun logout(): String {
        return "logout"
    }

    @GetMapping("/refresh-token")
    fun refreshToken(): String {
        return "refresh-token"
    }
}