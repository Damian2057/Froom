package com.froom.user.model.command

import com.froom.user.model.domain.Gender
import jakarta.validation.constraints.NotBlank
import java.util.*

data class UpdateUserCommand(

    @field:NotBlank(message = "Email is required")
    val email: String,

    @field:NotBlank(message = "Username is required")
    val userName: String,

    @field:NotBlank(message = "Birth date is required")
    val birthDate: Date,

    @field:NotBlank(message = "Gender is required")
    val gender: Gender
)
