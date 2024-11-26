package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.collection.Attachment
import com.example.physicistscard.transmissionModels.collection.AttachmentType
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
import kotlinx.uuid.fromString
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
        workId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
        authorId = "理天帝",
        title = "量子力学概论",
        description = "本作品介绍了量子力学的基本概念与应用，包括量子态、叠加原理和测量等。",
        categories = listOf("物理", "量子力学"),
        tags = listOf("量子", "物理学", "科学研究"),
        attachment = exampleAttachments,
        updateDate = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
        authorId = "我是明明啊",
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
        workId = UUID("B8370B6E-5546-4CB2-AF70-E2C81D2D2C66"),
        authorId = "子洋哥哥",
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
    ),
    Work(
        workId = UUID("8e937640-9fa1-4699-b823-924f524dfdd4"),
        authorId = "泰德·莫斯比",
        title = "脑机接口技术的研究",
        description = "探讨脑机接口技术的最新研究进展，分析其在医疗和增强现实领域的潜力。",
        categories = listOf("神经科学", "计算机科学"),
        tags = listOf("脑机接口", "增强现实", "医疗科技"),
        attachment = listOf(
            Attachment(
                attachmentId = 6,
                workId = UUID(),
                attachmentUrl = "https://example.com/研究论文.pdf",
                attachmentName = "脑机接口论文",
                attachmentSize = 20480L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(25, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID("fc3e2239-61a8-4961-93d1-b5a87e5b521d"),
        authorId = "杰斯",
        title = "3D打印技术的工业应用",
        description = "探索3D打印技术在制造业中的广泛应用，包括其优势和未来的发展方向。",
        categories = listOf("工业技术", "机械工程"),
        tags = listOf("3D打印", "制造业", "工业革命"),
        attachment = listOf(
            Attachment(
                attachmentId = 7,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/3D模型.zip",
                attachmentName = "打印模型文件",
                attachmentSize = 51200L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(12, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID("47e59a2a-df12-4e9b-97d3-f843f6d48ea8"),
        authorId = "星辰大海与人间地狱",
        title = "航天器热控制系统研究",
        description = "研究航天器热控制技术的原理和实现，分析其对太空任务的关键意义。",
        categories = listOf("航天工程", "物理"),
        tags = listOf("航天器", "热控制", "太空技术"),
        attachment = listOf(
            Attachment(
                attachmentId = 8,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/热控设计图.pdf",
                attachmentName = "热控设计图",
                attachmentSize = 30720L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(8, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(30, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID("38b2136e-3a4c-40a7-b6fa-e2bb412d3cf5"),
        authorId = "没头脑的盖茨比",
        title = "生态恢复技术与实践",
        description = "介绍生态恢复技术的理论与实际应用，特别是在湿地和森林的修复中。",
        categories = listOf("环境科学", "生态学"),
        tags = listOf("生态恢复", "环境保护", "可持续发展"),
        attachment = listOf(
            Attachment(
                attachmentId = 9,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/生态报告.pdf",
                attachmentName = "生态恢复报告",
                attachmentSize = 10240L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(4, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(9, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(22, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    ),
    Work(
        workId = UUID("edfdc432-4f5c-4914-8f71-13a7bda94864"),
        authorId = "不养猫的薛定谔",
        title = "自然语言处理的进展",
        description = "分析自然语言处理技术在语义理解和机器翻译中的新突破。",
        categories = listOf("计算机科学", "人工智能"),
        tags = listOf("NLP", "语义理解", "机器翻译"),
        attachment = listOf(
            Attachment(
                attachmentId = 10,
                workId = UUID.generateUUID(Random),
                attachmentUrl = "https://example.com/代码示例.zip",
                attachmentName = "自然语言处理代码",
                attachmentSize = 20480L,
                attachmentType = FILE,
                uploadAt = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
            )
        ),
        updateDate = Clock.System.now().minus(8, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
        submitDate = Clock.System.now().minus(18, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)

val exampleWorkStatuses = listOf(
    WorkReviewStatus.PENDING,
    WorkReviewStatus.APPROVED,
    WorkReviewStatus.REJECTED
)
