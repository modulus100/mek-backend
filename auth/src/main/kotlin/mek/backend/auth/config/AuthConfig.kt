package mek.backend.auth.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["mek.backend.auth.repository"])
@EntityScan("mek.backend.auth.model.entity")
@ComponentScan(basePackages = ["mek.backend.auth"])
@Configuration
class AuthConfig {

}