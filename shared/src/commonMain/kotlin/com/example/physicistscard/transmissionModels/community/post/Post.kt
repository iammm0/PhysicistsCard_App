import com.example.physicistscard.transmissionModels.community.content.Content
import com.example.physicistscard.utils.LocalDateTimeSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val postId: String,
    val userId: String,
    val title: String,
    val contents: List<Content>, // 使用多态序列化
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: Instant,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: Instant?,
    val category: String?, // 分类
    val tags: List<String> // 标签
)
