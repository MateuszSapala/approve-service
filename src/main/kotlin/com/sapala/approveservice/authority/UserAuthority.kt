package com.sapala.approveservice.authority

import com.sapala.approveservice.users.AppUser
import javax.persistence.*

@Entity
data class UserAuthority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @ManyToOne
    val user: AppUser,
    val authority: Authority,
)