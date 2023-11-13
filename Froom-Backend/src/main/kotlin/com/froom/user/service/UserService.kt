package com.froom.user.service

import com.froom.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository
) {

    fun getUser(): String {
        return "Hello World"
    }
}