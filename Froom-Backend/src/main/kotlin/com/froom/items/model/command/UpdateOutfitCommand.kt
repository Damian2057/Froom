package com.froom.items.model.command

import jakarta.validation.constraints.NotBlank

data class UpdateOutfitCommand(
    @field:NotBlank(message = "Name is required")
    val name: String
)
