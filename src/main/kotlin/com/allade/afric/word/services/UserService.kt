package com.allade.afric.word.services

import com.allade.afric.word.model.User
import com.allade.afric.word.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) {
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

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    fun findAll(): List<User> = userRepository.findAll()

    fun checkIfExists(email: String): Boolean = userRepository.existsByEmail(email)

    fun findById(id: UUID): User? = userRepository.findById(id)
}
