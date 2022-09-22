package com.sapala.approveservice.users

data class AppUserWithAuthorities(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val username: String,
    val password: String,
    val authorities: List<String>,
) {
    constructor(user: AppUser, authorities: List<String>) : this(
        id = user.id!!,
        name = user.name,
        surname = user.surname,
        email = user.email,
        username = user.username,
        password = user.password,
        authorities = authorities
    )
}