package com.froom.user.service

import com.froom.authorization.model.command.RegisterCommand
import com.froom.authorization.service.HashService
import com.froom.exception.type.UserNotFoundException
import com.froom.user.model.domain.User
import com.froom.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val hashService: HashService
) {

    fun getUser(): String {
        return "Hello World"
    }

    fun findById(userId: Long): User? {
        return userRepository.findById(userId).orElseThrow {
            UserNotFoundException("User not found")
        }
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email).orElseThrow {
            UserNotFoundException("User with email $email not found")
        }
    }

    fun isEmailExists(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun createUser(command: RegisterCommand): User {
        return userRepository.save(
            User(
                id = null,
                userName = command.userName,
                email = command.email,
                password = hashService.hashBcrypt(command.password),
                birthDate = command.birthDate
            )
        )
    }
}