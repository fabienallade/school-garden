package com.allade.afric.word.services

import com.allade.afric.word.model.User
import com.allade.afric.word.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    fun createUser(user: User): User? {
        val found = userRepository.findByEmail(user.email)
        if (found == null) {
            user.password = passwordEncoder.encode(user.password)
            println("User ${user.password} created")
            userRepository.save(user)
            return user
        }
        return null
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun checkIfExists(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

}