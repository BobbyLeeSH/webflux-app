package com.bobby.webfluxapp.controller

import com.bobby.webfluxapp.entity.Whisky
import com.bobby.webfluxapp.entity.WhiskyCreateDto
import com.bobby.webfluxapp.service.WhiskyService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("whisky")
class WhiskyController(
    private val whiskyService: WhiskyService
) {
    @GetMapping
    suspend fun getAllWhisky(): Flow<Whisky> {
        return whiskyService.getAll()
    }

    @GetMapping("/{id}")
    suspend fun getWhisky(@PathVariable id: String): Whisky {
        return whiskyService.getOne(id.toLong()) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No Whisky found.")
    }

    @PostMapping
    suspend fun createWhisky(@RequestBody whiskyCreateDto: WhiskyCreateDto): Whisky {
        return whiskyService.create(whiskyCreateDto.name, whiskyCreateDto.type)
    }
}