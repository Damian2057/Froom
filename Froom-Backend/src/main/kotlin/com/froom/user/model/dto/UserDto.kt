package com.froom.user.model.dto

import java.util.Date

data class UserDto(
    val email: String,
    val userName: String,
    val birthDate: Date,
)
