package com.sapala.approveservice.security

import com.sapala.approveservice.authority.UserAuthorityService
import com.sapala.approveservice.util.AuthorizationUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("approve-service/api/v1/authenticate/")
@Tag(name = "Authenticate")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val authorityService: UserAuthorityService,
    private val tokenProvider: TokenProvider,
) {
    companion object {
        private val log = LoggerFactory.getLogger(AuthenticationController::class.java)
    }

    @PostMapping("login")
    @Operation(
        summary = "Login",
        description = "Upon successful login, the API returns a JSON Web Token in the authorization header. Add this header to be able to send successful requests to authorized endpoints. In the Swagger UI, you paste the JSON Web Token without the initial word \"Bearer\", because the swagger adds it itself."
    )
    fun login(@RequestBody loginDetails: LoginDetails): ResponseEntity<Void> {
        log.info("Login user: ${loginDetails.username}")
        val authorities = authorityService
            .getUserAuthorities(loginDetails.username)
            .map { a -> GrantedAuthority { a } }
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginDetails.username,
                loginDetails.password,
                authorities
            )
        )
        val jwt = tokenProvider.createToken(authentication)
        log.info("User ${loginDetails.username} successfully logged")
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer $jwt").build()
    }

    @SecurityRequirement(name = "Authorization")
    @GetMapping("verify")
    @Operation(summary = "Verify user", description = "Return user details based od Authorization header")
    fun verify(): ResponseEntity<Any> {
        log.info("Verify token")
        return ResponseEntity.ok().body(AuthorizationUtil.loggedAppUser)
    }
}