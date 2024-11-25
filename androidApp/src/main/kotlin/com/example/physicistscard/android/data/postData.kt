package com.example.physicistscard.android.data

import Post
import com.example.physicistscard.transmissionModels.community.content.ImageContent
import com.example.physicistscard.transmissionModels.community.content.TextContent
import com.example.physicistscard.transmissionModels.community.content.VideoContent
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.uuid.UUID
import kotlinx.uuid.fromString

val examplePosts = listOf(
    Post(
        postId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        userId = "user001",
        title = "量子计算的前沿进展",
        description = "量子计算作为未来计算的核心技术之一，正在深刻改变科学和工程的面貌。",
        contents = listOf(
            TextContent(
                contentId = 1,
                order = 1,
                text = "量子计算是当前计算机科学领域的热点研究之一..."
            ),
            ImageContent(
                contentId = 2,
                order = 2,
                imageUrl = "https://example.com/images/quantum_computing.png"
            )
        ),
        createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        categories = listOf("科学技术", "量子计算"),
        tags = listOf("量子计算", "科技前沿", "计算机科学")
    ),
    Post(
        postId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        userId = "user002",
        title = "人工智能学习指南",
        description = "为人工智能初学者提供详细的学习路径，涵盖数学、算法和实际应用。",
        contents = listOf(
            TextContent(
                contentId = 3,
                order = 1,
                text = "学习人工智能需要从基础数学开始，包括线性代数和概率论..."
            ),
            VideoContent(
                contentId = 4,
                order = 2,
                videoUrl = "https://example.com/videos/ai_tutorial.mp4"
            )
        ),
        createdAt = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        categories = listOf("教育", "人工智能"),
        tags = listOf("人工智能", "深度学习", "数学基础")
    ),
    Post(
        postId = UUID("b8370b6e-5546-4cb2-af70-e2c81d2d2c66"),
        userId = "user003",
        title = "探索可再生能源",
        description = "深入探讨太阳能、风能等可再生能源的开发与应用技术。",
        contents = listOf(
            TextContent(
                contentId = 5,
                order = 1,
                text = "太阳能和风能是目前最具潜力的可再生能源..."
            ),
            ImageContent(
                contentId = 6,
                order = 2,
                imageUrl = "https://example.com/images/renewable_energy.png"
            ),
            VideoContent(
                contentId = 7,
                order = 3,
                videoUrl = "https://example.com/videos/renewable_energy.mp4"
            )
        ),
        createdAt = Clock.System.now().minus(20, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        categories = listOf("环境保护", "新能源"),
        tags = listOf("新能源", "太阳能", "风能", "可持续发展")
    ),
    Post(
        postId = UUID("8e937640-9fa1-4699-b823-924f524dfdd4"),
        userId = "user004",
        title = "现代艺术表达方式",
        description = "现代艺术的表达形式不断突破传统，展现创意与文化的碰撞。",
        contents = listOf(
            TextContent(
                contentId = 8,
                order = 1,
                text = "现代艺术强调自由表达，突破传统框架..."
            ),
            ImageContent(
                contentId = 9,
                order = 2,
                imageUrl = "https://example.com/images/modern_art.png"
            )
        ),
        createdAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        updatedAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        categories = listOf("艺术", "现代艺术"),
        tags = listOf("现代艺术", "创意", "文化")
    )
)
