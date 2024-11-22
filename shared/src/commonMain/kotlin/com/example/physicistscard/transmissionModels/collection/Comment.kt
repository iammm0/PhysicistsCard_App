package com.example.physicistscard.transmissionModels.collection

import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Suppress("PLUGIN_IS_NOT_ENABLED")
@kotlinx.serialization.Serializable
data class Comment(
    val commentId: Int,
    val userId: String,
    @kotlinx.serialization.Serializable(with = UUIDSerializer::class)
    val workId: UUID,
    val content: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val commentDate: LocalDateTime // ISO 8601 日期时间格式
)