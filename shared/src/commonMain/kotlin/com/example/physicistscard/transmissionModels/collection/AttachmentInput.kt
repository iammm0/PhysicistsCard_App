package com.example.physicistscard.transmissionModels.collection

import kotlinx.serialization.Serializable

@Serializable
data class AttachmentInput(
    val attachmentUrl: String,
    val attachmentName: String,
    val attachmentSize: Long,
    val attachmentType: String // 用户提交时使用字符串，服务层转为枚举
)
