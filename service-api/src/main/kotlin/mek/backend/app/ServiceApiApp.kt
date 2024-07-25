package mek.backend.app

import mek.backend.app.config.ServiceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(value = [ServiceConfig::class])
@SpringBootApplication
class ServiceApiApp

fun main(args: Array<String>) {
    runApplication<ServiceApiApp>(*args)
}