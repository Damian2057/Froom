package com.froom.authorization.model.command

data class LoginCommand(
    val email: String,
    val password: String
)
