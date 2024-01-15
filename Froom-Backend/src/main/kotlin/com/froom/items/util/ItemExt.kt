package com.froom.items.util

import com.froom.items.model.domain.Item
import com.froom.items.model.dto.ItemDto

fun Item.toDto(): ItemDto {
    return ItemDto(
        uuid = this.uuid,
        categoryType = this.categoryType,
        color = this.color,
        image = this.image,
    )
}