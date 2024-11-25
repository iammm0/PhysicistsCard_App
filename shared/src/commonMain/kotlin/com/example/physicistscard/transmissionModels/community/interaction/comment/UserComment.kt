import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class UserComment(
    val commentId: String,
    val userId: String, // 发表评论的用户ID
    @Serializable(with = UUIDSerializer::class)
    val postId: UUID, // 被评论的目标对象ID
    val content: String, // 评论内容
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: Instant, // 评论时间
    val parentId: String? = null // 父评论ID，用于支持评论的嵌套回复，顶级评论此字段为null
)