package com.antigravity.currentaffairs.domain.usecase

import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.repository.NewsRepository
import javax.inject.Inject

class FetchDailyAffairsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Result<List<CurrentAffair>> {
        return try {
            val affairs = newsRepository.fetchAndStoreDailyAffairs()
            Result.success(affairs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
