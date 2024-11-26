package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.community.post.MyPost
import com.example.physicistscard.transmissionModels.community.post.PostReviewStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.uuid.UUID

val myPosts = listOf(
    MyPost(
        postId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        userId = "我是明明啊",
        title = "量子计算的前沿进展",
        description = "量子计算作为未来计算的核心技术之一...",
        tags = listOf("量子计算", "科技前沿"),
        createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        reviewStatus = PostReviewStatus.APPROVED
    ),
    MyPost(
        postId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        userId = "我是明明啊",
        title = "人工智能学习指南",
        description = "为人工智能初学者提供详细的学习路径...",
        tags = listOf("人工智能", "深度学习"),
        createdAt = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        reviewStatus = PostReviewStatus.REJECTED,
        rejectedReason = "内容不符合平台规范"
    )
)

val likedPosts = listOf(
    MyPost(
        postId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        userId = "我是明明啊",
        title = "量子计算的前沿进展",
        description = "量子计算作为未来计算的核心技术之一...",
        tags = listOf("量子计算", "科技前沿"),
        createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        likedAt = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    MyPost(
        postId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        userId = "我是明明啊",
        title = "人工智能学习指南",
        description = "为人工智能初学者提供详细的学习路径...",
        tags = listOf("人工智能", "深度学习"),
        createdAt = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        likedAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)

val favoritedPosts = listOf(
    MyPost(
        postId = UUID("b8370b6e-5546-4cb2-af70-e2c81d2d2c66"),
        userId = "理天帝",
        title = "探索可再生能源",
        description = "深入探讨太阳能、风能等可再生能源的开发与应用技术...",
        tags = listOf("新能源", "太阳能", "可持续发展"),
        createdAt = Clock.System.now().minus(20, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        favoritedAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    MyPost(
        postId = UUID("8e937640-9fa1-4699-b823-924f524dfdd4"),
        userId = "李玲珑",
        title = "现代艺术表达方式",
        description = "现代艺术的表达形式不断突破传统...",
        tags = listOf("现代艺术", "创意", "文化"),
        createdAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        favoritedAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)
