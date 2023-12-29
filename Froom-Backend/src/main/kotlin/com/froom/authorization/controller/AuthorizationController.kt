package com.froom.authorization.controller

import com.froom.authorization.model.command.LoginAuthCommand
import com.froom.authorization.model.command.RefreshAuthCommand
import com.froom.authorization.model.dto.TokenDto
import com.froom.authorization.service.AuthorizationService
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
    fun login(@RequestBody @Valid command: LoginAuthCommand): ResponseEntity<TokenDto> {
        return ResponseEntity<TokenDto>(authorizationService.login(command),
            HttpStatus.ACCEPTED)
    }

    @PutMapping("/refresh")
    fun refresh(@RequestBody @Valid command: RefreshAuthCommand): ResponseEntity<TokenDto> {
        return ResponseEntity<TokenDto> (authorizationService.refreshToken(command),
            HttpStatus.ACCEPTED)
    }
}