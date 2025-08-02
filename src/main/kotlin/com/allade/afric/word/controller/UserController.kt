package com.allade.afric.word.controller

import com.allade.afric.word.model.User
import com.allade.afric.word.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController(
    @Autowired private val userService: UserService,
) {
    @RequestMapping("/", method = [RequestMethod.GET])
    fun index(): List<User> = userService.findAll()

    @RequestMapping("/{id}", method = [RequestMethod.GET])
    fun find(
        @PathVariable id: UUID,
    ): ResponseEntity<User> = userService.findById(id).toResponseEntity()

    private fun User?.toResponseEntity(): ResponseEntity<User> = this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}
