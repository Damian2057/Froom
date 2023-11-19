package com.froom.authorization.service

import com.froom.authorization.model.command.LoginCommand
import com.froom.authorization.model.command.RegisterCommand
import com.froom.authorization.model.dto.TokenDto
import com.froom.exception.type.InvalidCredentialsException
import com.froom.user.model.dto.UserDto
import com.froom.user.service.UserService
import com.froom.user.util.toDto
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val userService: UserService,
    private val hashService: HashService,
    private val tokenService: TokenService,
) {

    fun login(command: LoginCommand): TokenDto {
        val user = userService.findByEmail(command.email)

        if (user == null || !hashService.checkBcrypt(command.password, user.password)) {
            throw InvalidCredentialsException("Invalid credentials")
        }

        return tokenService.generateToken(user)
    }

    fun register(command: RegisterCommand): UserDto {
        if (userService.isEmailExists(command.email)) {
            throw InvalidCredentialsException("User with email ${command.email} already exists")
        }
        return userService.createUser(command).toDto()
    }

//    fun refresh(command: RefreshTokenCommand): TokenDto {
//        return TokenDto("token", "refreshToken", 1000, null, null)
//    }
}