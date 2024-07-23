package mek.backend.migrator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MigratorApp

fun main(args: Array<String>) {
    runApplication<MigratorApp>(*args)
}
