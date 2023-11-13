package com.froom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FroomApplication

fun main(args: Array<String>) {
    runApplication<FroomApplication>(*args)
}
