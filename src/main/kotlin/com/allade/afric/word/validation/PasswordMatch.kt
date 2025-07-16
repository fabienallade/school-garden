package com.allade.afric.word.validation

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [PasswordMatchValidator::class])
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordMatch(
    val message:String="The password doesn't match the password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = [])