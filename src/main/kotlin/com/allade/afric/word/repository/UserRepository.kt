package com.allade.afric.word.repository

import com.allade.afric.word.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}