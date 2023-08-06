package com.bobby.webfluxapp.controller

import com.bobby.webfluxapp.entity.Whisky
import com.bobby.webfluxapp.service.WhiskyService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import kotlinx.coroutines.flow.emptyFlow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.test.web.reactive.server.WebTestClient
import java.net.URI

@EnableR2dbcRepositories
@WebFluxTest(WhiskyController::class)
class WhiskyControllerTest(
    @Autowired private val webTestClient: WebTestClient
) {
    @MockkBean
    private lateinit var whiskyService: WhiskyService

    @Test
    fun `when GET all whisky and no whisky found then return empty`() {
        coEvery { whiskyService.getAll() } coAnswers { emptyFlow() }
        webTestClient
            .get().uri(URI("/whisky"))
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Whisky::class.java).hasSize(0)
    }
}
