package com.example.physicistscard.android.data

import UserComment
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.uuid.UUID

val exampleUserComments = listOf(
    UserComment(
        commentId = "1",
        userId = "user001",
        postId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        content = "这篇文章太棒了，真的学到了很多关于量子力学的知识！",
        createdAt = Instant.parse("2024-11-01T14:32:00Z"), // 添加 Z 表示 UTC 时区
        parentId = null // 顶级评论
    ),
    UserComment(
        commentId = "2",
        userId = "user002",
        postId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        content = "感谢你的分享！我也觉得这篇文章很精彩。",
        createdAt = Instant.parse("2024-11-01T15:12:00Z"), // 添加 Z 表示 UTC 时区
        parentId = "1" // 回复评论ID为1的评论
    ),
    UserComment(
        commentId = "3",
        userId = "user003",
        postId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        content = "人工智能学习指南提供了很多实用的知识，推荐给大家！",
        createdAt = Instant.parse("2024-11-02T09:45:00Z"), // 添加 Z 表示 UTC 时区
        parentId = null // 顶级评论
    ),
    UserComment(
        commentId = "4",
        userId = "user004",
        postId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        content = "很有帮助，我最近也在学习深度学习相关内容，受益匪浅！",
        createdAt = Instant.parse("2024-11-02T10:00:00Z"), // 添加 Z 表示 UTC 时区
        parentId = "3" // 回复评论ID为3的评论
    ),
    UserComment(
        commentId = "5",
        userId = "user005",
        postId = UUID("b8370b6e-5546-4cb2-af70-e2c81d2d2c66"),
        content = "新能源技术的未来发展方向写得很清晰，感谢作者的研究！",
        createdAt = Instant.parse("2024-11-03T08:30:00Z"), // 添加 Z 表示 UTC 时区
        parentId = null // 顶级评论
    ),
    UserComment(
        commentId = "6",
        userId = "user006",
        postId = UUID("b8370b6e-5546-4cb2-af70-e2c81d2d2c66"),
        content = "我对太阳能技术的未来应用很感兴趣，期待更多类似的文章。",
        createdAt = Instant.parse("2024-11-03T09:20:00Z"), // 添加 Z 表示 UTC 时区
        parentId = "5" // 回复评论ID为5的评论
    )
)

