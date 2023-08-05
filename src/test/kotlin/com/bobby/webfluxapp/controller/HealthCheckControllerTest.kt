package com.bobby.webfluxapp.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.net.URI

@WebFluxTest(HealthCheckController::class)
class HealthCheckControllerTest(
    @Autowired private val webTestClient: WebTestClient
) {

    @Test
    fun `when health check then return pong`() {
        webTestClient
            .get().uri(URI("/ping"))
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("pong!")
    }
}