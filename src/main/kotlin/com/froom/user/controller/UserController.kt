package com.froom.user.controller

import com.froom.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService
) {

    @GetMapping()
    fun getUser(): String {
        return userService.getUser();
    }


}