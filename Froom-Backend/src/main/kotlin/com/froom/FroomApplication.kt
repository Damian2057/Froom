package com.froom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
class FroomApplication

fun main(args: Array<String>) {
    runApplication<FroomApplication>(*args)
}
