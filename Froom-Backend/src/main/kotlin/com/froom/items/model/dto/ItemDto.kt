package com.froom.items.model.dto

import com.froom.items.model.domain.CategoryType
import java.util.UUID

data class ItemDto(
    val uuid: UUID,
    val categoryType: CategoryType,
    val color: List<Int>,
    val image: String,
    val imageFormat: String
)
