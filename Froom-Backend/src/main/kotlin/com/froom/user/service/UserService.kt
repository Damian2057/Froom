package com.froom.user.service

import com.froom.user.model.command.RegisterUserCommand
import com.froom.authorization.service.HashService
import com.froom.exception.type.InvalidCredentialsException
import com.froom.exception.type.UserNotFoundException
import com.froom.user.model.command.UpdateUserPasswordCommand
import com.froom.user.model.command.UpdateUserCommand
import com.froom.user.model.domain.User
import com.froom.user.model.dto.UserDto
import com.froom.user.repository.UserRepository
import com.froom.user.util.toDto
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService (
    private val userRepository: UserRepository,
    private val hashService: HashService
) {

    fun getUser(user: User): UserDto {
        return user.toDto()
    }

    fun findByUuid(uuid: UUID): User? {
        return userRepository.findByUuid(uuid).orElseThrow {
            UserNotFoundException("User not found")
        }
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun removeUser(user: User): UserDto {
        userRepository.delete(user)

        return user.toDto()
    }

    fun updateUser(user: User, command: UpdateUserCommand): UserDto {
        val existingUserByEmail = findByEmail(command.email)
        if (existingUserByEmail != null && existingUserByEmail.uuid != user.uuid) {
            throw InvalidCredentialsException("User with email ${command.email} already exists")
        }
        user.email = command.email
        user.userName = command.userName
        user.birthDate = command.birthDate
        user.gender = command.gender

        return userRepository.save(user).toDto()

    }

    fun updatePassword(user: User, command: UpdateUserPasswordCommand): UserDto {
        return null!!
    }

    fun registerUser(command: RegisterUserCommand): UserDto {
        if (isEmailExists(command.email)) {
            throw InvalidCredentialsException("User with email ${command.email} already exists")
        }
        return createUser(command).toDto()
    }

    private fun createUser(command: RegisterUserCommand): User {
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