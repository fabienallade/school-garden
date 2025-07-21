package com.allade.afric.word.config

import com.allade.afric.word.model.User
import com.allade.afric.word.repository.RoleRepository
import com.allade.afric.word.repository.UserRepository
import org.springframework.boot.CommandLineRunner

class DataLoader(private val userRepository: UserRepository, private val roleRepository: RoleRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        userRepository.deleteAll()
        roleRepository.deleteAll()
        for (i in 1..2){
            userRepository.save(User("admin", "admin", "admin",""))
        }
    }
}