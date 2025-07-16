package com.allade.afric.word.controller

import com.allade.afric.word.config.JwtProperties
import com.allade.afric.word.services.AuthUserService
import com.allade.afric.word.services.JwtService
import com.allade.afric.word.validation.ConfirmPasswordInterface
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping(path = ["/api/auth"])
class AuthController(val authenticationManager: AuthenticationManager,
    val authUserService: AuthUserService,
    val jwtService: JwtService,
    val jwtProperties: JwtProperties
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest:LoginRequest): AuthenticationResponse{
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
            loginRequest.email,
            loginRequest.password
        ))

        val user = authUserService.loadUserByUsername(loginRequest.email)

        val accessToken = jwtService.generate(user,Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))
        return AuthenticationResponse(accessToken)
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody registerRequest: RegisterRequest){

    }
}

data class LoginRequest(
    @field:Email val email: String,
    @field:NotBlank @field:Size(min = 8, max = 200) val password: String)
data class AuthenticationResponse(val token:String)

data class RegisterRequest(
    @field:NotBlank val name:String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(min = 8, max = 200) override val password: String,
    @field:NotBlank override val confirmPassword: String
):ConfirmPasswordInterface