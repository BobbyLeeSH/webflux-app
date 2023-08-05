package com.bobby.webfluxapp.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HealthCheckController {

    @GetMapping("ping")
    fun healthCheck(): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.ok("pong!"))
    }
}