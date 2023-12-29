package com.froom.authorization.controller

import com.froom.authorization.model.command.LoginCommand
import com.froom.authorization.model.command.RefreshTokenCommand
import com.froom.authorization.model.command.RegisterCommand
import com.froom.authorization.model.dto.TokenDto
import com.froom.authorization.service.AuthorizationService
import com.froom.user.model.dto.UserDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthorizationController(
    val authorizationService: AuthorizationService
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid command: LoginCommand): ResponseEntity<TokenDto> {
        return ResponseEntity<TokenDto>(authorizationService.login(command),
            HttpStatus.OK)
    }

    @PutMapping("/refresh")
    fun refresh(@RequestBody @Valid command: RefreshTokenCommand): ResponseEntity<TokenDto> {
        return ResponseEntity<TokenDto> (authorizationService.refreshToken(command),
            HttpStatus.OK)
    }
}