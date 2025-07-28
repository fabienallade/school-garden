package com.allade.afric.word.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(e: MethodArgumentNotValidException): ResponseEntity<Map<String, HashMap<String, String?>>> {
        val errors: HashMap<String, String?> = HashMap()
        e.bindingResult.fieldErrors.forEach { field ->
            errors[field.field] = field.defaultMessage
        }

        return ResponseEntity(mapOf("errors" to errors), HttpStatus.UNPROCESSABLE_ENTITY)
    }
}
