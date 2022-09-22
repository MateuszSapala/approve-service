package com.sapala.approveservice.security

import com.sapala.approveservice.authority.UserAuthorityService
import com.sapala.approveservice.users.AppUser
import com.sapala.approveservice.users.AppUserWithAuthorities
import com.sapala.approveservice.users.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val bCrypt: BCryptPasswordEncoder,
    private val userService: UserService,
    private val authorityService: UserAuthorityService,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val appUser: AppUser = userService.getUserByUsername(username)
        val authorities = authorityService.getUserAuthorities(appUser.id!!)
            .map { a -> GrantedAuthority { a } }
        return org.springframework.security.core.userdetails.User(
            appUser.username,
            bCrypt.encode(appUser.password),
            authorities
        )
    }

    fun getUserByUsername(username: String): AppUserWithAuthorities {
        val user = userService.getUserByUsername(username)
        val authorities = authorityService.getUserAuthorities(user.id!!)
        return AppUserWithAuthorities(user, authorities)
    }
}