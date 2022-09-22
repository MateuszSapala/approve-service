package com.sapala.approveservice.users

import com.sapala.approveservice.exception.CustomException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun addUser(appUser: AppUser): AppUser = userRepository.save(appUser)

    fun getUsers(): List<AppUser> = userRepository.findAll()

    fun getUser(id: Long): AppUser = userRepository.findById(id)
        .orElseThrow { CustomException(404, "User with id=$id not found") }

    fun getUserByUsername(username: String): AppUser = userRepository.findByUsername(username)
        .orElseThrow { CustomException(404, "User with username=$username not found") }
}