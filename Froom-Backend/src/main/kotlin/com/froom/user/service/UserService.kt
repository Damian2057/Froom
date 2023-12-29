package com.froom.user.service

import com.froom.authorization.model.command.RegisterCommand
import com.froom.authorization.service.HashService
import com.froom.exception.type.InvalidCredentialsException
import com.froom.exception.type.UserNotFoundException
import com.froom.user.model.command.UpdatePasswordCommand
import com.froom.user.model.command.UpdateUserCommand
import com.froom.user.model.domain.User
import com.froom.user.model.dto.UserDto
import com.froom.user.repository.UserRepository
import com.froom.user.util.toDto
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val hashService: HashService
) {

    fun getUser(user: User): UserDto {
        return user.toDto()
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

    fun removeUser(user: User): UserDto {
        userRepository.delete(user)
        return user.toDto()
    }

    fun updateUser(toUser: User, command: UpdateUserCommand): UserDto {
        return null!!
    }

    fun updatePassword(toUser: User, command: UpdatePasswordCommand): UserDto {
        return null!!
    }

    fun registerUser(command: RegisterCommand): UserDto {
        if (isEmailExists(command.email)) {
            throw InvalidCredentialsException("User with email ${command.email} already exists")
        }
        return createUser(command).toDto()
    }

    private fun createUser(command: RegisterCommand): User {
        return userRepository.save(
            User(
                id = null,
                userName = command.userName,
                email = command.email,
                password = hashService.hashBcrypt(command.password),
                birthDate = command.birthDate,
                gender = command.gender
            )
        )
    }

    private fun isEmailExists(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}