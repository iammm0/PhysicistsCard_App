package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.collection.MyWork
import com.example.physicistscard.transmissionModels.collection.WorkReviewStatus
import kotlinx.datetime.Instant
import kotlinx.uuid.UUID

val exampleMyWorks = listOf(
    MyWork(
        workId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        authorId = "user001",
        title = "量子力学概论",
        description = "介绍量子力学的基础知识，涵盖量子态、叠加原理与测量。",
        tags = listOf("量子", "物理学"),
        submitDate = Instant.parse("2024-11-01T14:32:00Z"),
        updateDate = Instant.parse("2024-11-03T10:45:00Z"),
        reviewStatus = WorkReviewStatus.PENDING,
        isLiked = false,
        isFavorited = true
    ),
    MyWork(
        workId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        authorId = "user002",
        title = "深度学习应用",
        description = "探讨深度学习在图像识别中的应用，提供实际案例分析。",
        tags = listOf("AI", "机器学习"),
        submitDate = Instant.parse("2024-10-30T08:30:00Z"),
        updateDate = Instant.parse("2024-11-01T14:12:00Z"),
        reviewStatus = WorkReviewStatus.APPROVED,
        isLiked = true,
        isFavorited = false
    ),
    MyWork(
        workId = UUID("b8370b6e-5546-4cb2-af70-e2c81d2d2c66"),
        authorId = "user003",
        title = "新能源技术探索",
        description = "分析太阳能与风能的最新研究进展，提出多能互补方案。",
        tags = listOf("新能源", "可持续发展"),
        submitDate = Instant.parse("2024-10-20T12:15:00Z"),
        updateDate = Instant.parse("2024-11-02T16:25:00Z"),
        reviewStatus = WorkReviewStatus.REJECTED,
        isLiked = false,
        isFavorited = false
    ),
    MyWork(
        workId = UUID("8e937640-9fa1-4699-b823-924f524dfdd4"),
        authorId = "user004",
        title = "脑机接口技术的研究",
        description = "探讨脑机接口技术的最新研究进展及其在医疗领域的应用。",
        tags = listOf("脑机接口", "增强现实", "医疗科技"),
        submitDate = Instant.parse("2024-10-15T09:45:00Z"),
        updateDate = Instant.parse("2024-11-01T10:30:00Z"),
        reviewStatus = WorkReviewStatus.APPROVED,
        isLiked = true,
        isFavorited = true
    ),
    MyWork(
        workId = UUID("38b2136e-3a4c-40a7-b6fa-e2bb412d3cf5"),
        authorId = "user005",
        title = "生态恢复技术与实践",
        description = "介绍生态恢复技术的理论与实际应用，特别是湿地和森林修复。",
        tags = listOf("生态恢复", "环境保护", "可持续发展"),
        submitDate = Instant.parse("2024-10-10T10:00:00Z"),
        updateDate = Instant.parse("2024-11-04T08:00:00Z"),
        reviewStatus = WorkReviewStatus.PENDING,
        isLiked = false,
        isFavorited = false
    )
)
