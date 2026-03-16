package com.antigravity.currentaffairs.domain.engine

import com.antigravity.currentaffairs.utils.TextUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentEnricher @Inject constructor() {

    fun cleanTitle(title: String): String {
        var cleaned = TextUtils.cleanHtml(title)

        // Remove source suffixes like "- The Hindu", "| Reuters"
        cleaned = cleaned.replace(Regex("\\s*[-|]\\s*[A-Z][^-|]*$"), "").trim()

        // Limit to 15 words
        val words = cleaned.split("\\s+".toRegex())
        if (words.size > 15) {
            cleaned = words.take(15).joinToString(" ") + "…"
        }

        // Ensure first letter is capitalized
        cleaned = cleaned.replaceFirstChar { c ->
            if (c.isLowerCase()) c.titlecase() else c.toString()
        }

        return cleaned
    }

    fun extractKeyPoints(description: String): List<String> {
        if (description.isBlank()) {
            return getDefaultKeyPoints()
        }

        val cleaned = TextUtils.cleanHtml(description)

        // Split by sentence separators
        val sentences = cleaned.split(Regex("[.।!?]+"))
            .map { it.trim() }
            .filter { it.length > 20 && it.length < 200 }
            .map { normalizeKeyPoint(it) }
            .distinct()

        return when {
            sentences.size >= 5 -> sentences.take(5)
            sentences.size >= 3 -> sentences.take(sentences.size)
            sentences.isNotEmpty() -> {
                val points = sentences.toMutableList()
                while (points.size < 3) {
                    points.add(getFillerPoint(points.size))
                }
                points
            }
            else -> getDefaultKeyPoints()
        }
    }

    private fun normalizeKeyPoint(text: String): String {
        var normalized = text.trim()

        // Capitalize first letter
        normalized = normalized.replaceFirstChar { c ->
            if (c.isLowerCase()) c.titlecase() else c.toString()
        }

        // Remove trailing punctuation and add period
        normalized = normalized.trimEnd('.', ',', ';', ':', '!', '?')

        return normalized
    }

    private fun getDefaultKeyPoints(): List<String> {
        return listOf(
            "Important development for competitive exam preparation",
            "Key fact relevant for SSC CGL and Railways exams",
            "Significant current affairs update to remember"
        )
    }

    private fun getFillerPoint(index: Int): String {
        val fillers = listOf(
            "This is an important development for competitive exam aspirants",
            "Key fact to remember for upcoming SSC and Railways examinations",
            "Significant update relevant for government exam preparation",
            "Important current affairs point for Banking and SSC exams",
            "Noteworthy development to include in exam revision notes"
        )
        return fillers.getOrElse(index) { fillers.first() }
    }

    fun generateShareText(
        title: String,
        keyPoints: List<String>
    ): String {
        val pointsText = keyPoints.joinToString("\n") { "• $it" }
        return """
            |$title
            |
            |Key Points:
            |$pointsText
            |
            |Read more on AntiGravity App
            |#CurrentAffairs #SSC #Railways
        """.trimMargin()
    }
}
