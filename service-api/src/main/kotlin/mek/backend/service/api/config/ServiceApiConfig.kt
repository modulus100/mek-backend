package mek.backend.service.api.config

import mek.backend.auth.config.AuthConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@ComponentScan(basePackages = ["mek.backend.service.api"])
@Import(value = [AuthConfig::class])
class ServiceApiConfig {
}