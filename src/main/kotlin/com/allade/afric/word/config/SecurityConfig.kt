package com.allade.afric.word.config

import com.allade.afric.word.filters.JwtAuthenticationFilter
import com.allade.afric.word.services.AuthUserService
import com.allade.afric.word.services.UserService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(userService: UserService): UserDetailsService = AuthUserService(userService)

    @Bean
    fun authenticationProvider(userService: UserService): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(userService))
                it.setPasswordEncoder(passwordEncoder())
            }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager? = authConfig.authenticationManager

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter,
        authenticationProvider: AuthenticationProvider,
    ): DefaultSecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
//            .authorizeHttpRequests {
//                it
//                    .requestMatchers("/api/auth/**")
//                    .permitAll()
//                    .requestMatchers(HttpMethod.POST, "/api/**")
//                    .hasRole("ADMIN")
//                    .anyRequest()
//                    .fullyAuthenticated()
//            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)
