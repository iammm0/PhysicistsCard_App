package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.collection.Comment
import com.example.physicistscard.transmissionModels.collection.CommentReply
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.uuid.UUID

// 示例评论数据
val exampleComments = listOf(
    Comment(
        commentId = 1,
        userId = "理天帝",
        workId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        content = "这篇文章真的很好，让我对量子力学有了更深的了解！",
        commentDate = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        replies = listOf(
            CommentReply(
                replyId = 101,
                commentId = 1,
                userId = "量子大牛",
                content = "感谢支持，我会继续努力！",
                replyDate = Clock.System.now().minus(12, DateTimeUnit.HOUR, TimeZone.currentSystemDefault())
            ),
            CommentReply(
                replyId = 102,
                commentId = 1,
                userId = "学术新人",
                content = "请问能否推荐一些入门书籍？",
                replyDate = Clock.System.now().minus(8, DateTimeUnit.HOUR, TimeZone.currentSystemDefault())
            )
        )
    ),
    Comment(
        commentId = 2,
        userId = "我是明明啊",
        workId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        content = "人工智能的学习指南太实用了，非常感谢作者的分享！",
        commentDate = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        replies = listOf(
            CommentReply(
                replyId = 103,
                commentId = 2,
                userId = "AI工程师",
                content = "很高兴这篇文章对你有帮助！",
                replyDate = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        )
    ),
    Comment(
        commentId = 3,
        userId = "子洋哥哥",
        workId = UUID("B8370B6E-5546-4CB2-AF70-E2C81D2D2C66"),
        content = "新能源的技术探索很棒，希望能有更多关于多能互补的文章！",
        commentDate = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        replies = emptyList()
    )
)