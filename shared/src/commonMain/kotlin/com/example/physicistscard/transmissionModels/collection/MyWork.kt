package com.example.physicistscard.transmissionModels.collection

import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class MyWork(
    @Serializable(with = UUIDSerializer::class)
    val workId: UUID,
    val authorId: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val submitDate: Instant, // 提交日期
    @Serializable(with = LocalDateTimeSerializer::class)
    val updateDate: Instant, // 更新日期
    val reviewStatus: WorkReviewStatus, // 审核状态
    val isLiked: Boolean, // 是否被当前用户点赞
    val isFavorited: Boolean // 是否被当前用户收藏
)
