package com.allade.afric.word.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordMatchValidator: ConstraintValidator<PasswordMatch, ConfirmPasswordInterface> {
    override fun isValid(request: ConfirmPasswordInterface, context: ConstraintValidatorContext): Boolean {
        val isValid = request.password == request.confirmPassword

        if (!isValid) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                .addPropertyNode("passwordConfirmation")
                .addConstraintViolation()
        }

        return isValid
    }
}

interface ConfirmPasswordInterface {
    val password: String
    val confirmPassword: String
}