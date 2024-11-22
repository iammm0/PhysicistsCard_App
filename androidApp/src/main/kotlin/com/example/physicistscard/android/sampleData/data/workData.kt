package com.example.physicistscard.android.sampleData.data

import com.example.physicistscard.transmissionModels.collection.Attachment
import com.example.physicistscard.transmissionModels.collection.AttachmentType
import com.example.physicistscard.transmissionModels.collection.Work
import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID
import kotlin.random.Random


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
    val updateDate: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val submitDate: LocalDateTime,
)

val exampleWorks = listOf(
    Work(
        workId = UUID.generateUUID(Random),
        authorId = "user001",
        title = "量子计算的前沿进展",
        description = "量子计算是当前计算机科学领域的热点研究之一，涉及到许多新兴的数学和物理学原理。",
        categories = listOf("物理学", "计算机科学"),
        tags = listOf("量子计算", "科技前沿", "计算机科学"),
        attachment = listOf(
            Attachment(
                attachmentId = 1,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/量子计算论文.pdf",
                attachmentName = "量子计算的基础理论",
                attachmentSize = 2048L,
                attachmentType = AttachmentType.FILE,
                uploadAt = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            ),
            Attachment(
                attachmentId = 2,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/量子计算图示.png",
                attachmentName = "量子计算图示",
                attachmentSize = 512L,
                attachmentType = AttachmentType.IMAGE,
                uploadAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID.generateUUID(Random),
        authorId = "user002",
        title = "人工智能学习指南",
        description = "这篇文章探讨了学习人工智能的路径，尤其是深度学习和神经网络的应用。",
        categories = listOf("计算机科学", "人工智能"),
        tags = listOf("人工智能", "深度学习", "机器学习"),
        attachment = listOf(
            Attachment(
                attachmentId = 3,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/深度学习教程.pdf",
                attachmentName = "深度学习基础教程",
                attachmentSize = 3072L,
                attachmentType = AttachmentType.FILE,
                uploadAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            ),
            Attachment(
                attachmentId = 4,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/深度学习演示.mp4",
                attachmentName = "深度学习演示视频",
                attachmentSize = 10240L,
                attachmentType = AttachmentType.VIDEO,
                uploadAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(8, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID.generateUUID(Random),
        authorId = "user003",
        title = "可再生能源的未来",
        description = "探索太阳能和风能的最新技术，以及它们如何实现全球能源转型。",
        categories = listOf("环境科学", "能源"),
        tags = listOf("新能源", "可持续发展", "太阳能", "风能"),
        attachment = listOf(
            Attachment(
                attachmentId = 5,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/可再生能源报告.pdf",
                attachmentName = "可再生能源技术报告",
                attachmentSize = 4096L,
                attachmentType = AttachmentType.FILE,
                uploadAt = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            ),
            Attachment(
                attachmentId = 6,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/太阳能发电图示.png",
                attachmentName = "太阳能发电图示",
                attachmentSize = 1024L,
                attachmentType = AttachmentType.IMAGE,
                uploadAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)

