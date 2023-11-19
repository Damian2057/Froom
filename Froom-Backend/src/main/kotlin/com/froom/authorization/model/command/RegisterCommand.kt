package com.froom.authorization.model.command

import java.util.Date

data class RegisterCommand(
    val email: String,
    val password: String,
    val userName: String,
    val birthDate: Date
)
