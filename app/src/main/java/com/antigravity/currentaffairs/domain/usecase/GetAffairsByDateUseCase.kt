package com.antigravity.currentaffairs.domain.usecase

import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAffairsByDateUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(date: String): Flow<List<CurrentAffair>> {
        return newsRepository.getAffairsByDate(date)
    }

    fun byCategory(date: String, category: String): Flow<List<CurrentAffair>> {
        return newsRepository.getAffairsByDateAndCategory(date, category)
    }

    suspend fun getById(id: String): CurrentAffair? {
        return newsRepository.getAffairById(id)
    }
}
