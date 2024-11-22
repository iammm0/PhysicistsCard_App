package com.example.physicistscard.transmissionModels.collection

import com.example.physicistscard.utils.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class CommentReply(
    val replyId: Int,
    val commentId: Int,
    val userId: String,
    val content: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val replyDate: LocalDateTime
)
