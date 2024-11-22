package com.example.physicistscard.transmissionModels.collection

// 作品审核状态
enum class WorkReviewStatus(val status: String) {
    PENDING("Pending"),  // 待审核
    APPROVED("Approved"), // 审核通过
    REJECTED("Rejected"); // 审核拒绝

    companion object {
        // 根据状态字符串获取对应的枚举
        fun fromString(status: String): WorkReviewStatus? {
            return values().find { it.status.equals(status, ignoreCase = true) }
        }
    }
}
