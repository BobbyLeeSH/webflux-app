package com.bobby.webfluxapp.service

import com.bobby.webfluxapp.entity.Whisky
import com.bobby.webfluxapp.enums.WhiskyType
import com.bobby.webfluxapp.repository.WhiskyRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service


@Service
class WhiskyService(
    private val whiskyRepository: WhiskyRepository
) {
    suspend fun getAll(): Flow<Whisky> {
        return whiskyRepository.findAll()
    }

    suspend fun getOne(id: Long): Whisky? {
        return whiskyRepository.findById(id)
    }

    suspend fun create(name: String, type: WhiskyType): Whisky {
        return whiskyRepository.save(Whisky(name = name, type = type))
    }
}