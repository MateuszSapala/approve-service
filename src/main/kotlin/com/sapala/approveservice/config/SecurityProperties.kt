package com.sapala.approveservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

@ConfigurationProperties(prefix = "jwt-security")
@Validated
class SecurityProperties {
    @field:NotBlank
    @field:Size(min = 64)
    val secret = "s6v9y(B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2r5u8x/A?D(G+KbPeShVm"

    @field:Positive
    val expirationTime: Int = 31 // in days

    @field:Positive
    val strength = 10

    val tokenPrefix = "Bearer "
    val headerString = "Authorization"
}
