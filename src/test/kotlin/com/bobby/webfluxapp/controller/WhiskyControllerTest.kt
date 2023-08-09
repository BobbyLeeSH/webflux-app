package com.bobby.webfluxapp.controller

import com.bobby.webfluxapp.entity.Whisky
import com.bobby.webfluxapp.entity.WhiskyCreateDto
import com.bobby.webfluxapp.enums.WhiskyType
import com.bobby.webfluxapp.service.WhiskyService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.net.URI

@WebFluxTest(WhiskyController::class)
class WhiskyControllerTest(
    @Autowired private val webTestClient: WebTestClient
) {
    @MockkBean
    private lateinit var whiskyService: WhiskyService

    private val whisky1 = Whisky(name = "someWhisky", type = WhiskyType.BLENDED)
    private val whisky2 = Whisky(name = "someWhisky2", type = WhiskyType.BLENDED)

    @Test
    fun `when GET all whisky and no whisky found then return empty`() {
        coEvery { whiskyService.getAll() } coAnswers { emptyFlow() }
        webTestClient
            .get().uri(URI("/whisky"))
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Whisky::class.java).hasSize(0)
    }

    @Test
    fun `when GET all whisky found then return whisky list`() {
        coEvery { whiskyService.getAll() } coAnswers { flowOf(whisky1, whisky2) }
        webTestClient
                .get().uri(URI("/whisky"))
                .exchange()
                .expectStatus().isOk
                .expectBodyList(Whisky::class.java).hasSize(2).contains(whisky1, whisky2)
    }

    @Test
    fun `when GET one then return whisky`() {
        coEvery { whiskyService.getOne(any()) } coAnswers { whisky1 }
        webTestClient
                .get().uri(URI("/whisky/1"))
                .exchange()
                .expectStatus().isOk
                .expectBody(Whisky::class.java).isEqualTo(whisky1)
    }

    @Test
    fun `when GET one and nothing found then return 404`() {
        coEvery { whiskyService.getOne(any()) } coAnswers { null }
        webTestClient
                .get().uri(URI("/whisky/1"))
                .exchange()
                .expectStatus().isNotFound
    }

    @Test
    fun `when POST whisky then return whisky`() {
        coEvery { whiskyService.create(any(), any()) } coAnswers { whisky1 }
        webTestClient
                .post()
                .uri(URI("/whisky"))
                .body(BodyInserters.fromValue(WhiskyCreateDto(whisky1.name, whisky1.type)))
                .exchange()
                .expectStatus().isOk
                .expectBody(Whisky::class.java).isEqualTo(whisky1)
    }
}
