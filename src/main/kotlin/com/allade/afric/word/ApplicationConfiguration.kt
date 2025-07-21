package com.allade.afric.word

import com.allade.afric.word.config.DataLoader
import com.allade.afric.word.repository.RoleRepository
import com.allade.afric.word.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {
    @Bean
    fun initializer(userRepository: UserRepository,roleRepository: RoleRepository): CommandLineRunner {
        return DataLoader(userRepository, roleRepository)
    }
}