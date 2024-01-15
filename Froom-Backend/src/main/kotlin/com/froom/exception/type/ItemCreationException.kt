package com.froom.exception.type

class ItemCreationException(message: String, e: Throwable) : RuntimeException(message, e) {
}