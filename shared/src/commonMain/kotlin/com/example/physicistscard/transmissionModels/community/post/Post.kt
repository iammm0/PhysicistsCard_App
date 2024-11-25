import com.example.physicistscard.transmissionModels.community.content.Content
import com.example.physicistscard.utils.LocalDateTimeSerializer
import com.example.physicistscard.utils.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class Post(
    @Serializable(with = UUIDSerializer::class)
    val postId: UUID,
    val userId: String,
    val title: String,
    val description: String,
    val categories: List<String>, // 分类
    val tags: List<String>, // 标签
    val contents: List<Content>, // 使用多态序列化
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: Instant,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: Instant,
)