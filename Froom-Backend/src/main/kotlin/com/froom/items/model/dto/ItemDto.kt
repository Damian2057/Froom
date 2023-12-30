package com.froom.items.model.dto

import java.util.UUID

data class ItemDto(
    val uuid: UUID,
    val categoryType: String,
    val color: List<Int>,
    val image: ByteArray,
)
