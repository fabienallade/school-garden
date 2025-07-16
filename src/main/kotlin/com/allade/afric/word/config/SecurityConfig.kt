package com.allade.afric.word.config

import com.allade.afric.word.repository.UserRepository
import com.allade.afric.word.services.AuthUserService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService {
        return AuthUserService(userRepository)
    }

    @Bean
    fun authenticationProvider(userRepository: UserRepository):AuthenticationProvider =
        DaoAuthenticationProvider()
        .also {
            it.setUserDetailsService(userDetailsService(userRepository))
            it.setPasswordEncoder(passwordEncoder())
    }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager? {
        return authConfig.authenticationManager
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }

        return http.build()
    }

}

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)