package com.sapala.approveservice.authority

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AuthorityRepository : JpaRepository<UserAuthority, Long> {
    @Query("SELECT a FROM UserAuthority a WHERE a.user.id=:userId")
    fun findByUserId(@Param("userId") userId: Long): List<UserAuthority>

    @Query("SELECT a FROM UserAuthority a WHERE a.user.username=:username")
    fun findByUsername(@Param("username") username: String): List<UserAuthority>
}