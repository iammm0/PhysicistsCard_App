package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.collection.Attachment
import com.example.physicistscard.transmissionModels.collection.AttachmentType.FILE
import com.example.physicistscard.transmissionModels.collection.AttachmentType.IMAGE
import com.example.physicistscard.transmissionModels.collection.AttachmentType.VIDEO
import com.example.physicistscard.transmissionModels.collection.Work
import com.example.physicistscard.transmissionModels.collection.WorkReviewStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID
import kotlin.random.Random

val exampleAttachments = listOf(
    Attachment(
        attachmentId = 1,
        workId = UUID.generateUUID(Random),
        attachmentUrl = "https://example.com/文件.pdf",
        attachmentName = "物理学基础",
        attachmentSize = 2048L,
        attachmentType = FILE,
        uploadAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Attachment(
        attachmentId = 2,
        workId = UUID.generateUUID(Random),
        attachmentUrl = "https://example.com/实验视频.mp4",
        attachmentName = "量子力学实验",
        attachmentSize = 10240L,
        attachmentType = VIDEO,
        uploadAt = Clock.System.now().minus(13, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Attachment(
        attachmentId = 3,
        workId = UUID.generateUUID(Random),
        attachmentUrl = "https://example.com/实验图片.png",
        attachmentName = "实验结果图",
        attachmentSize = 5120L,
        attachmentType = IMAGE,
        uploadAt = Clock.System.now().minus(12, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)

val exampleWorks = listOf(
    Work(
        workId = UUID.generateUUID(Random),
        authorId = "user001",
        title = "量子力学概论",
        description = "本作品介绍了量子力学的基本概念与应用，包括量子态、叠加原理和测量等。",
        categories = listOf("物理", "量子力学"),
        tags = listOf("量子", "物理学", "科学研究"),
        attachment = exampleAttachments,
        updateDate = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID.generateUUID(Random),
        authorId = "user002",
        title = "人工智能与深度学习",
        description = "本作品探讨了深度学习在图像识别中的应用，涵盖了CNN架构及优化算法。",
        categories = listOf("计算机科学", "人工智能"),
        tags = listOf("深度学习", "计算机视觉", "AI"),
        attachment = listOf(
            Attachment(
                attachmentId = 4,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/代码.zip",
                attachmentName = "神经网络代码",
                attachmentSize = 30720L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID.generateUUID(Random),
        authorId = "user003",
        title = "新能源技术的探索",
        description = "本作品分析了太阳能与风能的最新研究进展，提出了多能互补的技术解决方案。",
        categories = listOf("能源", "环境科学"),
        tags = listOf("新能源", "太阳能", "风能", "可持续发展"),
        attachment = listOf(
            Attachment(
                attachmentId = 5,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/技术报告.pdf",
                attachmentName = "新能源技术报告",
                attachmentSize = 10240L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(20, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)

val exampleWorkStatuses = listOf(
    WorkReviewStatus.PENDING,
    WorkReviewStatus.APPROVED,
    WorkReviewStatus.REJECTED
)
