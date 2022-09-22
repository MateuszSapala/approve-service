package com.sapala.approveservice.users

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
data class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val surname: String,
    val email: String,
    @Column(unique = true)
    val username: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val password: String,
)