package com.froom.authorization.model.command

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class LoginCommand(
    @NotNull
    @field:NotBlank(message = "Email is required")
    val email: String,
    @NotNull
    @field:NotBlank(message = "Password is required")
    val password: String
)
