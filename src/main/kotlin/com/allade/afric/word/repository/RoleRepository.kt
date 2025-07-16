package com.allade.afric.word.repository

import com.allade.afric.word.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name:String): Role?
}