package com.allade.afric.word

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/")
class Greeting {
    @RequestMapping("/")
    fun hello() = "Hello World!"
}