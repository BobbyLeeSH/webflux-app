package com.bobby.webfluxapp.service

import com.bobby.webfluxapp.entity.Whisky
import com.bobby.webfluxapp.enums.WhiskyType
import com.bobby.webfluxapp.repository.WhiskyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WhiskyServiceTest {
    private val whiskyRepository = mockk<WhiskyRepository>()
    private val whiskyService = WhiskyService(whiskyRepository)

    private val whisky1 = Whisky(name = "someWhisky", type = WhiskyType.BLENDED)
    private val whisky2 = Whisky(name = "someWhisky2", type = WhiskyType.BLENDED)

    @Test
    fun `when getAll then return whisky list`(): Unit = runBlocking {
        coEvery { whiskyRepository.findAll() } coAnswers { flowOf(whisky1, whisky2) }
        val actual = whiskyService.getAll().toList()
        assertThat(actual).containsExactly(whisky1, whisky2)
    }

    @Test
    fun `when getAll and nothing found then return empty list`(): Unit = runBlocking {
        coEvery { whiskyRepository.findAll() } coAnswers { emptyFlow() }
        val actual = whiskyService.getAll().toList()
        assertThat(actual).isEmpty()
    }

    @Test
    fun `when getOne then return whisky`(): Unit = runBlocking {
        coEvery { whiskyRepository.findById(any()) } coAnswers { whisky1 }
        val actual = whiskyService.getOne(0)
        assertThat(actual).isEqualTo(whisky1)
    }

    @Test
    fun `when getOne and nothing found then return null`(): Unit = runBlocking {
        coEvery { whiskyRepository.findById(any()) } coAnswers { null }
        val actual = whiskyService.getOne(0)
        assertThat(actual).isNull()
    }

    @Test
    fun `when create then return whisky`(): Unit = runBlocking {
        coEvery { whiskyRepository.save(any()) } coAnswers { whisky1 }
        val actual = whiskyService.create("name", WhiskyType.BLENDED)
        assertThat(actual).isEqualTo(whisky1)
    }
}