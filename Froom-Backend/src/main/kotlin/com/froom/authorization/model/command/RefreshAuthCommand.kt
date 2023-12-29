package com.froom.authorization.model.command

data class RefreshAuthCommand(
    val refreshToken: String
)
