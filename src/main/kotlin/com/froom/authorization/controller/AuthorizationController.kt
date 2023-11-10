package com.froom.authorization.controller

import com.froom.authorization.model.dto.TokenDto
import com.froom.authorization.service.AuthorizationService
import com.froom.user.model.dto.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthorizationController(
    val authorizationService: AuthorizationService
) {

    @PostMapping("/login")
    fun login(): ResponseEntity<TokenDto> {
        return ResponseEntity<TokenDto>(null,
            org.springframework.http.HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto> (null,
            org.springframework.http.HttpStatus.OK)
    }

    @PutMapping("/refresh")
    fun refresh(): ResponseEntity<TokenDto> {
        return ResponseEntity<TokenDto> (null,
            org.springframework.http.HttpStatus.OK)
    }
}