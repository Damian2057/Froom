package com.froom.util.retrofit.color.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ColorResponseDto(
    @JsonProperty("RGB")
    val rgb: List<Int>
) {
    constructor() : this(emptyList())
}