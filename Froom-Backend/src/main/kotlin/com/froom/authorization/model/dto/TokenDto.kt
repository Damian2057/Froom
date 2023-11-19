package com.froom.authorization.model.dto

import com.froom.user.model.dto.UserDto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String?,
    val expiresIn: Long,
    val unit: String,
    val tokenType: String,
    val user: UserDto,
)
