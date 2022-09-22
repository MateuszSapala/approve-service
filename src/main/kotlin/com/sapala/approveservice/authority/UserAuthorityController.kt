package com.sapala.approveservice.authority

import com.sapala.approveservice.users.UserController
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/approve-service/api/v1/authority")
@Tag(name = "Authorities")
class UserAuthorityController(
    private val authorityService: UserAuthorityService,
) {
    companion object {
        private val log = LoggerFactory.getLogger(UserController::class.java)
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROOT')")
    fun addAuthority(authority: UserAuthority): UserAuthority {
        log.info("Will add user ${authority.user.username} authority ${authority.authority.name}")
        return authorityService.addUserAuthorities(authority)
    }

    @GetMapping("/{userId}")
    fun getAuthorities(@PathVariable("userId") userId: Long): List<String> {
        log.info("Will get user $userId authorities")
        return authorityService.getUserAuthorities(userId)
    }
}