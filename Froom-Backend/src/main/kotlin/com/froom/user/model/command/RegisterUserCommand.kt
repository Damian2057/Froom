package com.froom.user.model.command

import com.froom.user.model.domain.Gender
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date

data class RegisterUserCommand(
    @field:NotBlank(message = "Email is required")
    @field:NotNull(message = "Gender is required")
    val email: String,

    @field:NotBlank(message = "Password is required")
    @field:NotNull
    val password: String,

    @field:NotBlank(message = "User name is required")
    @field:NotNull
    val userName: String,

    @field:NotNull(message = "Birth date is required")
    val birthDate: Date,

    @field:NotNull(message = "Gender is required")
    val gender: Gender = Gender.UNKNOWN
)
