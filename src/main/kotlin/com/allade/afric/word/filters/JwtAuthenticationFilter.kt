package com.allade.afric.word.filters

import com.allade.afric.word.services.AuthUserService
import com.allade.afric.word.services.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val userDetailsService: AuthUserService,
    private val jwtService: JwtService) : OncePerRequestFilter() {
        override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
            val authHeader = request.getHeader("Authorization")

            if (authHeader.doesNotContainBearerToken()) {
                filterChain.doFilter(request, response)
                return
            }

            val jwtToken = authHeader?.extractTokenValue()
            val email = jwtService.extractEmail(jwtToken!!)

            if (email.isBlank() && SecurityContextHolder.getContext().authentication == null) {
                val foundUser = userDetailsService.loadUserByUsername(email)

                if(jwtService.isValid(jwtToken, foundUser)) {
                    updateContext(foundUser,request)
                }
                filterChain.doFilter(request, response)
            }
        }

    private fun String?.doesNotContainBearerToken(): Boolean {
        return (this == null || !this.contains("Bearer"))
    }

    private fun String.extractTokenValue(): String {
        return this.substringAfter("Bearer ")
    }

    private fun updateContext(foundUser: UserDetails,request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser,null,foundUser.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }
}