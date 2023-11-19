package com.froom.exception.response

data class ErrorResponse(
    val message: String,
    val code: Int,
    val errors: Map<String, String>,
)
