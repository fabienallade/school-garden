package com.allade.afric.word.repository

import com.allade.afric.word.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : PagingAndSortingRepository<User, UUID> {
    fun findByEmail(email: String): User?

    fun save(user: User): User

    fun findAll(): List<User>

    fun deleteAll()

    fun existsByEmail(email: String): Boolean
}
