package com.antigravity.currentaffairs.utils

import com.antigravity.currentaffairs.data.model.Language
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    private val hindiMonths = mapOf(
        1 to "जनवरी", 2 to "फरवरी", 3 to "मार्च",
        4 to "अप्रैल", 5 to "मई", 6 to "जून",
        7 to "जुलाई", 8 to "अगस्त", 9 to "सितंबर",
        10 to "अक्टूबर", 11 to "नवंबर", 12 to "दिसंबर"
    )

    fun getPreviousDay(): LocalDate = LocalDate.now().minusDays(1)

    fun formatDate(date: LocalDate, language: Language): String {
        return when (language) {
            Language.ENGLISH -> date.format(
                DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
            )
            Language.HINDI -> {
                "${date.dayOfMonth} ${hindiMonths[date.monthValue]} ${date.year}"
            }
        }
    }

    fun formatDateShort(date: LocalDate, language: Language): String {
        return when (language) {
            Language.ENGLISH -> date.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
            )
            Language.HINDI -> {
                "${date.dayOfMonth} ${hindiMonths[date.monthValue]?.take(3)} ${date.year}"
            }
        }
    }

    fun getDateString(date: LocalDate): String {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    fun parseDate(dateStr: String): LocalDate? {
        return try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: Exception) {
            null
        }
    }

    fun parseIsoDate(dateStr: String): Long {
        val formats = listOf(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
        )

        for (format in formats) {
            try {
                return format.parse(dateStr)?.time ?: continue
            } catch (e: Exception) {
                continue
            }
        }
        return System.currentTimeMillis()
    }

    fun calculateDelayUntil6AM(): Long {
        val now = java.time.LocalDateTime.now()
        var target = now.toLocalDate().atTime(6, 0)
        if (now.isAfter(target)) {
            target = target.plusDays(1)
        }
        return java.time.Duration.between(now, target).toMillis()
    }

    fun isToday(dateStr: String): Boolean {
        return dateStr == getDateString(LocalDate.now())
    }

    fun isYesterday(dateStr: String): Boolean {
        return dateStr == getDateString(getPreviousDay())
    }
}
