package com.bobby.webfluxapp.entity


import com.bobby.webfluxapp.enums.WhiskyType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "WHISKY")
data class Whisky (
    @Id
    val id: Long = 0L,
    val name: String,
    val type: WhiskyType,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

data class WhiskyCreateDto(
    val name: String,
    val type: WhiskyType
)