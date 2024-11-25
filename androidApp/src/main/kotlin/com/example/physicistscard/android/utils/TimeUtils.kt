package com.example.physicistscard.android.utils

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.format.DateTimeFormatter
import java.time.Instant as JavaInstant
import java.time.LocalDateTime as JavaLocalDateTime
import java.time.ZoneId
import java.util.*

object TimeUtils {

    // 默认时间格式
    private val defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // TimeZone
    private val systemTimeZone: TimeZone = TimeZone.currentSystemDefault()
    private val javaSystemZone: ZoneId = ZoneId.systemDefault()

    /**
     * 格式化 Kotlinx LocalDateTime
     */
    fun formatKotlinxLocalDateTime(dateTime: LocalDateTime, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return dateTime.toJavaLocalDateTime().format(formatter)
    }

    /**
     * 格式化 Kotlinx Instant
     */
    fun formatKotlinxInstant(instant: Instant, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val javaInstant = JavaInstant.ofEpochMilli(instant.toEpochMilliseconds())
        return javaInstant.atZone(javaSystemZone).format(formatter)
    }

    /**
     * 格式化 Java LocalDateTime
     */
    fun formatJavaLocalDateTime(dateTime: JavaLocalDateTime, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return dateTime.format(formatter)
    }

    /**
     * 将 Kotlinx Instant 转为 Kotlinx LocalDateTime
     */
    fun instantToKotlinxLocalDateTime(instant: Instant): LocalDateTime {
        return instant.toLocalDateTime(systemTimeZone)
    }

    /**
     * 将 Java Instant 转为 Kotlinx LocalDateTime
     */
    fun javaInstantToKotlinxLocalDateTime(instant: JavaInstant): LocalDateTime {
        return Instant.fromEpochMilliseconds(instant.toEpochMilli()).toLocalDateTime(systemTimeZone)
    }

    /**
     * 获取当前时间（Kotlinx LocalDateTime）
     */
    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(systemTimeZone)
    }

    /**
     * 将字符串解析为 Kotlinx LocalDateTime
     */
    fun parseKotlinxLocalDateTime(dateTimeString: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val javaDateTime = JavaLocalDateTime.parse(dateTimeString, formatter)
        return javaDateTime.toKotlinLocalDateTime()
    }

    /**
     * 将字符串解析为 Java LocalDateTime
     */
    fun parseJavaLocalDateTime(dateTimeString: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): JavaLocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return JavaLocalDateTime.parse(dateTimeString, formatter)
    }
}
