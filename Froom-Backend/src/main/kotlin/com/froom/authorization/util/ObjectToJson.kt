package com.froom.authorization.util

import com.fasterxml.jackson.databind.ObjectMapper

fun Any.toStringJSON(): String {
    return ObjectMapper().writeValueAsString(this)
}