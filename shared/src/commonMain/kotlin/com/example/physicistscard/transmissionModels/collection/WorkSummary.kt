package com.example.physicistscard.transmissionModels.collection

import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class WorkSummary(
    @Serializable(with = UUIDSerializer::class)
    val workId: UUID,
    val authorId: String,
    val title: String,
    val description: String
)