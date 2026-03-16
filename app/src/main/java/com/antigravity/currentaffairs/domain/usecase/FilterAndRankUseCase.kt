package com.antigravity.currentaffairs.domain.usecase

import com.antigravity.currentaffairs.data.model.RawNewsItem
import com.antigravity.currentaffairs.domain.engine.ContentFilter
import com.antigravity.currentaffairs.domain.engine.RelevanceScorer
import javax.inject.Inject

class FilterAndRankUseCase @Inject constructor(
    private val contentFilter: ContentFilter,
    private val relevanceScorer: RelevanceScorer
) {
    operator fun invoke(items: List<RawNewsItem>): List<Pair<RawNewsItem, Int>> {
        return contentFilter.filterRelevantNews(items)
            .map { item -> Pair(item, relevanceScorer.calculateScore(item)) }
            .sortedByDescending { it.second }
    }
}
