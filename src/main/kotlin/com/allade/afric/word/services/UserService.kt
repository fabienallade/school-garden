package com.allade.afric.word.services

import com.allade.afric.word.model.User
import com.allade.afric.word.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): User {
        val found = userRepository.findByEmail(user.email)
        if (found != null) {
            userRepository.save(user)
        }
        return userRepository.save(user)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

}