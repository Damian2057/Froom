package com.froom.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/user")
class UserController {
    
    @GetMapping()
    fun getUser(): String {
        return "Hello World"
    }


}