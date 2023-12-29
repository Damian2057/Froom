package com.froom.user.model.command

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateUserPasswordCommand(
    @NotNull
    @field:NotBlank(message = "Old password is required")
    val oldPassword: String,
    @NotNull
    @field:NotBlank(message = "New password is required")
    val newPassword: String,
    @NotNull
    @field:NotBlank(message = "Confirm password is required")
    val confirmPassword: String,
)
