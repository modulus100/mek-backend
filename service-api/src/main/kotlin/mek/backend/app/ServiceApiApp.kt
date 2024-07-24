package mek.backend.app

import mek.backend.auth.config.AuthConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(value = [AuthConfig::class])
@SpringBootApplication
class ServiceApiApp

fun main(args: Array<String>) {
    runApplication<ServiceApiApp>(*args)
}