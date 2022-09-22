package com.sapala.approveservice.users

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/approve-service/api/v1/user")
@Tag(name = "Users")
class UserController(
    private val userService: UserService,
) {
    companion object {
        private val log = LoggerFactory.getLogger(UserController::class.java)
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROOT')")
    fun addUser(@RequestBody appUser: AppUser): AppUser {
        log.info("Will add user ${appUser.username}")
        return userService.addUser(appUser)
    }

    fun getUsers(): List<AppUser> {
        log.info("Will get all users")
        return userService.getUsers()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long): AppUser {
        log.info("Will get user $id")
        return userService.getUser(id)
    }
}