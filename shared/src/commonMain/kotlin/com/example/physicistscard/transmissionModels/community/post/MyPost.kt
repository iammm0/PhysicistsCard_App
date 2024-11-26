package com.example.physicistscard.transmissionModels.community.post

import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class MyPost(
    @Serializable(with = UUIDSerializer::class)
    val postId: UUID, // 推送ID
    val userId: String, // 作者ID
    val title: String, // 标题
    val description: String, // 描述
    val tags: List<String>, // 标签
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: Instant, // 创建时间
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: Instant, // 更新时间
    val reviewStatus: PostReviewStatus? = null, // 审核状态（仅用于我发布的推送）
    val rejectedReason: String? = null, // 驳回原因（仅用于我发布的推送）
    @Serializable(with = LocalDateTimeSerializer::class)
    val likedAt: Instant? = null, // 点赞时间（仅用于我点赞的推送）
    @Serializable(with = LocalDateTimeSerializer::class)
    val favoritedAt: Instant? = null // 收藏时间（仅用于我收藏的推送）
)
