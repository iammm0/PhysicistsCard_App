package com.example.physicistscard.android.data

import Post
import com.example.physicistscard.transmissionModels.community.content.ImageContent
import com.example.physicistscard.transmissionModels.community.content.TextContent
import com.example.physicistscard.transmissionModels.community.content.VideoContent
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus

val examplePosts = listOf(
    Post(
        postId = "post001",
        userId = "user001",
        title = "量子计算的前沿进展",
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
        category = "科学技术",
        tags = listOf("量子计算", "科技前沿", "计算机科学")
    ),
    Post(
        postId = "post002",
        userId = "user002",
        title = "人工智能学习指南",
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
        category = "教育",
        tags = listOf("人工智能", "深度学习", "数学基础")
    ),
    Post(
        postId = "post003",
        userId = "user003",
        title = "探索可再生能源",
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
        category = "环境保护",
        tags = listOf("新能源", "太阳能", "风能", "可持续发展")
    ),
    Post(
        postId = "post004",
        userId = "user004",
        title = "现代艺术表达方式",
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
        category = "艺术",
        tags = listOf("现代艺术", "创意", "文化")
    )
)
