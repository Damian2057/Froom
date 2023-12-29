package com.froom.authorization.service

import com.froom.authorization.model.command.LoginCommand
import com.froom.authorization.model.command.RefreshTokenCommand
import com.froom.authorization.model.dto.TokenDto
import com.froom.exception.type.InvalidCredentialsException
import com.froom.user.service.UserService
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

    fun refreshToken(command: RefreshTokenCommand): TokenDto {
        return null!!
    }
}