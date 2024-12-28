package com.allade.afric.word

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@RestController
class Application
fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
