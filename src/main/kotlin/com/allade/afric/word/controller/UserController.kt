package com.allade.afric.word.controller

import com.allade.afric.word.model.User
import com.allade.afric.word.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userRepository: UserRepository) {

    @RequestMapping("/", method = [RequestMethod.GET])
    fun index(): List<User> {
        return userRepository.findAll()
    }
}