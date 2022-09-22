package com.sapala.approveservice.authority

import org.springframework.stereotype.Service

@Service
class UserAuthorityService(
    private val authorityRepository: AuthorityRepository,
) {
    fun getUserAuthorities(userId: Long): List<String> = authorityRepository.findByUserId(userId)
        .map { a -> a.authority.name }

    fun getUserAuthorities(username: String): List<String> =
        authorityRepository.findByUsername(username).map { a -> a.authority.name }

    fun addUserAuthorities(userAuthority: UserAuthority) = authorityRepository.save(userAuthority)
}