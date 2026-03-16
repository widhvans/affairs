package com.antigravity.currentaffairs.utils

import org.jsoup.Jsoup

object TextUtils {

    fun cleanHtml(html: String): String {
        if (html.isBlank()) return ""
        return try {
            Jsoup.parse(html).text()
                .replace(Regex("\\s+"), " ")
                .replace(Regex("<[^>]+>"), "")
                .trim()
        } catch (e: Exception) {
            html.replace(Regex("<[^>]+>"), "")
                .replace(Regex("\\s+"), " ")
                .trim()
        }
    }

    fun truncate(text: String, maxLength: Int): String {
        if (text.length <= maxLength) return text
        return text.take(maxLength - 3) + "…"
    }

    fun capitalizeFirst(text: String): String {
        return text.replaceFirstChar { c ->
            if (c.isLowerCase()) c.titlecase() else c.toString()
        }
    }

    fun extractDomain(url: String): String {
        return try {
            val domain = url.removePrefix("https://")
                .removePrefix("http://")
                .removePrefix("www.")
                .substringBefore("/")
                .substringBefore("?")
            domain
        } catch (e: Exception) {
            url
        }
    }

    fun hashTitle(title: String): String {
        val cleaned = title.lowercase()
            .replace(Regex("[^a-z0-9\\s]"), "")
            .replace(Regex("\\s+"), " ")
            .trim()
        return cleaned.hashCode().toString()
    }
}
