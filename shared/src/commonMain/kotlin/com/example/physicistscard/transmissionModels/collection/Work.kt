package com.example.physicistscard.transmissionModels.collection

import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class Work(
    @Serializable(with = UUIDSerializer::class)
    val workId: UUID,
    val authorId: String,
    val title: String,
    val description: String,
    val categories: List<String>,
    val tags: List<String>,
    val attachment: List<Attachment>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updateDate: Instant,
    @Serializable(with = LocalDateTimeSerializer::class)
    val submitDate: Instant,
)