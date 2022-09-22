package com.sapala.approveservice.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sapala.approveservice.config.SecurityProperties
import com.sapala.approveservice.users.AppUser
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val securityProperties: SecurityProperties,
    private val tokenProvider: TokenProvider,
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?,
    ): Authentication {
        return try {
            val mapper = jacksonObjectMapper()

            val credentials = mapper
                .readValue<AppUser>(req.inputStream)

            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    credentials.username,
                    credentials.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw AuthenticationServiceException(e.message)
        }
    }

    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain?,
        authentication: Authentication,
    ) {
        val token = tokenProvider.createToken(authentication)
        res.addHeader(securityProperties.headerString, securityProperties.tokenPrefix + token)
    }
}
