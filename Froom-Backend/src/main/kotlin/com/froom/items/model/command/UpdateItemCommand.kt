package com.froom.items.model.command

import com.froom.items.model.domain.CategoryType
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateItemCommand(
    @field:NotNull(message = "Category is required")
    val category: CategoryType,
    @field:Size(min = 1, max=3, message = "Color is required")
    val color: List<Int>
)
