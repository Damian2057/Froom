package com.froom.user.model.dto

import java.util.Date

data class UserDto(
    val email: String,
    val firstName: String,
    val lastName: String,
    val birthDate: Date,
)
