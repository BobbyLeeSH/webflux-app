package com.bobby.webfluxapp.repository

import com.bobby.webfluxapp.entity.Whisky
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WhiskyRepository: CoroutineCrudRepository<Whisky, Long> {
}