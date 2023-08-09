package com.bobby.webfluxapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxAppApplication

fun main(args: Array<String>) {
    runApplication<WebfluxAppApplication>(*args)
}
