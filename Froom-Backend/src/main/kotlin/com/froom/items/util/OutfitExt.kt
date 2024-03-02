package com.froom.items.util

import com.froom.items.model.domain.Outfit
import com.froom.items.model.dto.OutfitDto

fun Outfit.toDto(): OutfitDto {
    return OutfitDto(
        uuid = this.uuid,
        name = this.name,
        items = this.items.map { it.toDto() }
    )
}