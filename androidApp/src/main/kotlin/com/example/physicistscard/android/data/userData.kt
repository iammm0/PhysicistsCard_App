package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.auth.user.Role
import com.example.physicistscard.transmissionModels.auth.user.User
import kotlinx.datetime.LocalDateTime

val exampleUsers = listOf(
    User(
        userId = "user001",
        username = "理天帝",
        email = "litiandi@example.com",
        phone = "12345678901",
        passwordHash = "hashed_password_001",
        avatarUrl = "https://example.com/avatar/litiandi.jpg",
        bio = "探索物理世界的秘密",
        registerDate = LocalDateTime.parse("2024-01-01T10:30:00"),
        isEmailVerified = true,
        isPhoneVerified = true,
        role = Role.USER
    ),
    User(
        userId = "user002",
        username = "我是明明啊",
        email = "mingming@example.com",
        phone = "12345678902",
        passwordHash = "hashed_password_002",
        avatarUrl = "https://example.com/avatar/mingming.jpg",
        bio = "人工智能研究者",
        registerDate = LocalDateTime.parse("2024-01-01T10:30:00"),
        isEmailVerified = true,
        isPhoneVerified = true,
        role = Role.USER
    ),
    User(
        userId = "user003",
        username = "子洋哥哥",
        email = "ziyang@example.com",
        phone = "12345678903",
        passwordHash = "hashed_password_003",
        avatarUrl = "https://example.com/avatar/ziyang.jpg",
        bio = "新能源领域探索者",
        registerDate = LocalDateTime.parse("2024-01-01T10:30:00"),
        isEmailVerified = true,
        isPhoneVerified = false,
        role = Role.USER
    )
)
