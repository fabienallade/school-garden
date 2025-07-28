package com.allade.afric.word.services

import com.allade.afric.word.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import com.allade.afric.word.model.User as UserApplication

@Service
class AuthUserService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository
            .findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")

    private fun UserApplication.mapToUserDetails(): UserDetails =
        User
            .builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role?.name)
            .build()
}
