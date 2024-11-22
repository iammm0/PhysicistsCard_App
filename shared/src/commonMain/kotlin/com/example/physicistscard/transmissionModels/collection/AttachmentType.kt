package com.example.physicistscard.transmissionModels.collection

enum class AttachmentType(val type: String) {
    FILE("File"),
    VIDEO("Video"),
    IMAGE("Image");

    companion object {
        // 根据状态字符串获取对应的枚举
        fun fromString(status: String): AttachmentType? {
            return values().find { it.type.equals(status, ignoreCase = true) }
        }
    }
}