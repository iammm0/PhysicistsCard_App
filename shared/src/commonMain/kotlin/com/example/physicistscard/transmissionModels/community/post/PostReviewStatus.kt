package com.example.physicistscard.transmissionModels.community.post

enum class PostReviewStatus(val status: String) {
    PENDING("Pending"),   // 待审核
    APPROVED("Approved"), // 审核通过
    REJECTED("Rejected"), // 审核拒绝
    REVOKED("Revoked");

    companion object {
        /**
         * 根据状态字符串获取对应的枚举
         * @param status 状态字符串
         * @return 对应的 PostReviewStatus 枚举，如果找不到则返回 null
         */
        fun fromString(status: String): PostReviewStatus? {
            return entries.find { it.status.equals(status, ignoreCase = true) }
        }

        /**
         * 获取所有状态的字符串列表
         */
        fun allStatuses(): List<String> {
            return entries.map { it.status }
        }
    }
}
