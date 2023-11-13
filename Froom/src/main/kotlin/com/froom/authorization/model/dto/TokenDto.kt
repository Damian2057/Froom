package com.froom.authorization.model.dto

data class TokenDto(
    val token: String,
    val refreshToken: String,
    val expiresIn: Long,
    val tokenType: String = "Bearer"
)
