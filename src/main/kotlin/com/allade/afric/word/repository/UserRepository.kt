package com.allade.afric.word.repository

import com.allade.afric.word.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository: PagingAndSortingRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun save(user: User): User
    fun findAll(): List<User>
    fun deleteAll()
}