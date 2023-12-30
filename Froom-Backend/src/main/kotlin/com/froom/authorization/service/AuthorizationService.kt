package com.froom.authorization.service

import com.froom.authorization.model.command.LoginAuthCommand
import com.froom.authorization.model.command.RefreshAuthCommand
import com.froom.authorization.model.dto.TokenDto
import com.froom.exception.type.InvalidCredentialsException
import com.froom.user.model.domain.User
import com.froom.user.service.UserService
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val userService: UserService,
    private val hashService: HashService,
    private val tokenService: TokenService,
) {

    fun login(command: LoginAuthCommand): TokenDto {
        val user = userService.findByEmail(command.email)
        if (user == null || !hashService.checkBcrypt(command.password, user.password)) {
            throw InvalidCredentialsException("Invalid credentials")
        }

        return tokenService.generateToken(user)
    }

    fun refreshToken(command: RefreshAuthCommand): TokenDto {
        val user = getUserFromRefreshToken(command.refreshToken)
        return generateTokenForUser(user, command.refreshToken)
    }

    private fun getUserFromRefreshToken(refreshToken: String): User {
        return tokenService.checkRefreshTokenAndReturnUser(refreshToken)
    }

    private fun generateTokenForUser(user: User, refreshToken: String): TokenDto {
        val token = tokenService.generateToken(user)
        token.refreshToken = refreshToken
        return token
    }
}