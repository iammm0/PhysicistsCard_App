package com.example.physicistscard.transmissionModels.community.post

import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class MyPublishedPost(
    @Serializable(with = UUIDSerializer::class)
    val postId: UUID,
    val userId: String,
    val title: String,
    val description: String,
    val categories: List<String>,
    val tags: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: Instant,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: Instant,
    val reviewStatus: PostReviewStatus // 审核状态
)
