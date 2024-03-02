package com.froom.items.util

import com.froom.items.model.domain.Item
import com.froom.items.model.dto.ItemDto
import java.util.*

fun Item.toDto(): ItemDto {
    val base64Image: String = Base64.getEncoder().encodeToString(this.image)
    return ItemDto(
        uuid = this.uuid,
        categoryType = this.categoryType,
        color = this.color,
        image = base64Image,
        imageFormat = this.imageFormat
    )
}