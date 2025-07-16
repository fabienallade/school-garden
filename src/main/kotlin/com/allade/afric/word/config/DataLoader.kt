package com.allade.afric.word.config

import com.allade.afric.word.repository.UserRepository
import org.springframework.boot.CommandLineRunner

class DataLoader(val userRepository: UserRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        TODO("Not yet implemented")
    }
}