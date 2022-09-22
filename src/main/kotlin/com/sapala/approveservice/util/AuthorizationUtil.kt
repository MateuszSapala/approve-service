package com.sapala.approveservice.util

import com.sapala.approveservice.users.AppUserWithAuthorities
import org.springframework.security.core.context.SecurityContextHolder

class AuthorizationUtil {
    companion object {
        val loggedAppUser: AppUserWithAuthorities get() = SecurityContextHolder.getContext().authentication.principal as AppUserWithAuthorities
    }
}