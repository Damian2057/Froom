package com.froom.user.util

import com.froom.user.model.domain.User
import com.froom.user.model.dto.UserDto

fun User.toDto(): UserDto {
    return UserDto(
        uuid = this.uuid,
        email = this.email,
        userName = this.userName,
        birthDate = this.birthDate,
        gender = this.gender
    )
}