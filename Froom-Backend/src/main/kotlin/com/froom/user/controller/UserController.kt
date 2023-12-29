package com.froom.user.controller

import com.froom.user.model.command.RegisterUserCommand
import com.froom.authorization.util.toUser
import com.froom.user.model.command.UpdateUserPasswordCommand
import com.froom.user.model.command.UpdateUserCommand
import com.froom.user.model.dto.UserDto
import com.froom.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
) {

    @GetMapping()
    fun getUser(authentication: Authentication): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(userService.getUser(authentication.toUser()),
            HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid command: RegisterUserCommand): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto> (userService.registerUser(command),
            HttpStatus.OK)
    }

    @PutMapping()
    fun updateUser(authentication: Authentication,
                   @RequestBody @Valid command: UpdateUserCommand): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(userService.updateUser(authentication.toUser(), command),
            HttpStatus.OK)
    }

    @PutMapping("/password")
    fun updatePassword(authentication: Authentication,
                       @RequestBody @Valid command: UpdateUserPasswordCommand): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(userService.updatePassword(authentication.toUser(), command),
            HttpStatus.OK)
    }

    @DeleteMapping()
    fun deleteUser(authentication: Authentication): ResponseEntity<UserDto> {
        return ResponseEntity<UserDto>(userService.removeUser(authentication.toUser()),
            HttpStatus.OK)
    }

}