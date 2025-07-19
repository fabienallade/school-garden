package com.allade.afric.word.dto

import com.allade.afric.word.validation.ConfirmPasswordInterface
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserRegisterRequestDto(
    @field:NotBlank val firstName: String,
    @field:Email val email: String,
    @field:NotBlank val lastName: String,
    @field:NotBlank @field:Size(min = 8, max = 200) override val password: String,
    @field:NotBlank override val confirmPassword: String
): ConfirmPasswordInterface