package com.froom.user.model.dto

import com.froom.user.model.domain.Gender
import java.util.Date
import java.util.UUID

data class UserDto(
    val uuid: UUID,
    val email: String,
    val userName: String,
    val birthDate: Date,
    val gender: Gender
)
