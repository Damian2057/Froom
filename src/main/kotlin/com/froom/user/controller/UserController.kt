package com.froom.user.controller

import com.froom.user.model.dto.UserDto
import com.froom.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
) {

    @GetMapping()
    fun getUser(): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(null,
            HttpStatus.OK)
    }

    @PutMapping()
    fun putUser(): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(null,
            HttpStatus.OK)
    }

    @PutMapping("/password")
    fun putUserPassword(): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(null,
            HttpStatus.OK)
    }

    @DeleteMapping()
    fun deleteUser(): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(null,
            HttpStatus.OK)
    }

}