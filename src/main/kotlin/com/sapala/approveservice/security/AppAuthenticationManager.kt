package com.sapala.approveservice.security

import com.sapala.approveservice.exception.CustomException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class AppAuthenticationManager(
    private val userService: UserDetailsServiceImpl,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication? {
        val password = authentication.credentials.toString()
        val user = userService.loadUserByUsername(authentication.name)
        if (!bCryptPasswordEncoder.matches(password, user.password)) {
            throw CustomException(400, "Bad credentials")
        }
        return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
    }
}
