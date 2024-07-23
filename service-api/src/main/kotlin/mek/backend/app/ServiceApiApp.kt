package mek.backend.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ServiceApiApp

fun main(args: Array<String>) {
    runApplication<ServiceApiApp>(*args)
}