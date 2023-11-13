package com.froom.authorization.service

import com.froom.authorization.model.command.LoginCommand
import com.froom.authorization.model.command.RefreshTokenCommand
import com.froom.authorization.model.command.RegisterCommand
import com.froom.authorization.model.dto.TokenDto
import com.froom.user.model.dto.UserDto
import org.springframework.stereotype.Service
import java.sql.Date

@Service
class AuthorizationService {

    fun login(command: LoginCommand): TokenDto {
        return TokenDto("token", "refreshToken", 1000)
    }

    fun register(command: RegisterCommand): UserDto {
        return UserDto("email", "firstName", "lastName", Date.valueOf("2021-01-01"))
    }

    fun refresh(command: RefreshTokenCommand): TokenDto {
        return TokenDto("token", "refreshToken", 1000)
    }
}