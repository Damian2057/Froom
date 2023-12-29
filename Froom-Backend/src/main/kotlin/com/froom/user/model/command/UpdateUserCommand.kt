package com.froom.user.model.command

import com.froom.user.model.domain.Gender
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.*

data class UpdateUserCommand(

    @field:NotBlank(message = "Email is required")
    val email: String,

    @field:NotBlank(message = "Username is required")
    val userName: String,

    @field:NotNull(message = "Birth date is required")
    val birthDate: Date,

    @field:NotNull(message = "Gender is required")
    val gender: Gender
)
