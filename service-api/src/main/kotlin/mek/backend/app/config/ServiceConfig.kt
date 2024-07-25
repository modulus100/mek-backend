package mek.backend.app.config

import mek.backend.auth.config.AuthConfig
import mek.backend.iam.IamConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.EnableTransactionManagement

@Import(value = [
    AuthConfig::class,
    IamConfig::class,
    OpenApiConfig::class
])
@Configuration
@EnableTransactionManagement
class ServiceConfig {
}