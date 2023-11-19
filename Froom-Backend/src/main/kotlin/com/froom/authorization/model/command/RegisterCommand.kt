package com.froom.authorization.model.command

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.util.Date

data class RegisterCommand(
    @field:NotBlank(message = "Email is required")
    @field:NotNull
    val email: String,

    @field:NotBlank(message = "Password is required")
    @field:NotNull
    val password: String,

    @field:NotBlank(message = "User name is required")
    @field:NotNull
    val userName: String,

    @field:NotNull
    val birthDate: Date
)
