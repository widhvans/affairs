package com.antigravity.currentaffairs.domain.usecase

import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.domain.engine.SimilarityChecker
import javax.inject.Inject

class DeduplicateUseCase @Inject constructor(
    private val similarityChecker: SimilarityChecker
) {
    operator fun invoke(affairs: List<CurrentAffair>): List<CurrentAffair> {
        return similarityChecker.deduplicateAffairs(affairs)
    }
}
