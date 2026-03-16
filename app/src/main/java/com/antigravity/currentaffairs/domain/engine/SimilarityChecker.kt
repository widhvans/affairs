package com.antigravity.currentaffairs.domain.engine

import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.RawNewsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimilarityChecker @Inject constructor() {

    companion object {
        private const val SIMILARITY_THRESHOLD = 0.6
    }

    fun jaccardSimilarity(text1: String, text2: String): Double {
        val words1 = text1.lowercase()
            .replace(Regex("[^a-z0-9\\s]"), "")
            .split("\\s+".toRegex())
            .filter { it.length > 2 }
            .toSet()
        val words2 = text2.lowercase()
            .replace(Regex("[^a-z0-9\\s]"), "")
            .split("\\s+".toRegex())
            .filter { it.length > 2 }
            .toSet()

        if (words1.isEmpty() || words2.isEmpty()) return 0.0

        val intersection = words1.intersect(words2)
        val union = words1.union(words2)

        return if (union.isEmpty()) 0.0
        else intersection.size.toDouble() / union.size.toDouble()
    }

    fun deduplicateAffairs(affairs: List<CurrentAffair>): List<CurrentAffair> {
        val unique = mutableListOf<CurrentAffair>()
        for (affair in affairs.sortedByDescending { it.relevanceScore }) {
            val isDuplicate = unique.any { existing ->
                jaccardSimilarity(existing.titleEnglish, affair.titleEnglish) > SIMILARITY_THRESHOLD
            }
            if (!isDuplicate) {
                unique.add(affair)
            }
        }
        return unique
    }

    fun deduplicateRawItems(
        items: List<RawNewsItem>,
        existingTitles: List<String> = emptyList()
    ): List<RawNewsItem> {
        val unique = mutableListOf<RawNewsItem>()

        for (item in items) {
            // Check against existing titles in database
            val isDuplicateOfExisting = existingTitles.any { existing ->
                jaccardSimilarity(existing, item.title) > SIMILARITY_THRESHOLD
            }
            if (isDuplicateOfExisting) continue

            // Check against already selected items
            val isDuplicateOfSelected = unique.any { existing ->
                jaccardSimilarity(existing.title, item.title) > SIMILARITY_THRESHOLD
            }
            if (!isDuplicateOfSelected) {
                unique.add(item)
            }
        }

        return unique
    }
}
