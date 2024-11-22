package com.example.physicistscard.transmissionModels.collection

import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class Attachment(
    val attachmentId: Int,
    @Serializable(with = UUIDSerializer::class)
    val workId: UUID,
    val attachmentUrl: String,
    val attachmentName: String,
    val attachmentSize: Long,
    val attachmentType: AttachmentType,
    @Serializable(with = LocalDateTimeSerializer::class)
    val uploadAt: Instant,
)