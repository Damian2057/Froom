package com.froom.util.retrofit.category.model

data class CategoryResponseDto(
    val category: String,
    val confidence: Double
) {
    constructor() : this("", 0.0)
}
