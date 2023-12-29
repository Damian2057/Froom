package com.froom.user.model.dto

import com.froom.user.model.domain.Gender
import java.util.Date

data class UserDto(
    val email: String,
    val userName: String,
    val birthDate: Date,
    val gender: Gender
)
