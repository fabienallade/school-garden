package com.allade.afric.word.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/")
class HomeController {
    @RequestMapping(method = [RequestMethod.GET])
    fun hello() = "Hello World!"
}
