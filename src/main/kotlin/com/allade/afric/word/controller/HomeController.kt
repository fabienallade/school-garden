package com.allade.afric.word.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/")
class HomeController {
    @RequestMapping("/")
    fun hello() = "Hello World!"

    @RequestMapping("/greet")
    fun greet(): String = "Hello Fabien"
}
