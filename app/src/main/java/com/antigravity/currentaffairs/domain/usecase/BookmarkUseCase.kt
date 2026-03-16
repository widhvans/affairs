package com.antigravity.currentaffairs.domain.usecase

import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.repository.BookmarkRepository
import com.antigravity.currentaffairs.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val newsRepository: NewsRepository
) {
    fun getAllBookmarks(): Flow<List<CurrentAffair>> {
        return bookmarkRepository.getAllBookmarks()
    }

    fun searchBookmarks(query: String): Flow<List<CurrentAffair>> {
        return bookmarkRepository.searchBookmarks(query)
    }

    fun getAllBookmarkDates(): Flow<List<String>> {
        return bookmarkRepository.getAllBookmarkDates()
    }

    suspend fun toggleBookmark(affair: CurrentAffair): Boolean {
        val isCurrentlyBookmarked = bookmarkRepository.isBookmarked(affair.id)
        if (isCurrentlyBookmarked) {
            bookmarkRepository.removeBookmark(affair.id)
            newsRepository.updateBookmarkStatus(affair.id, false)
        } else {
            bookmarkRepository.addBookmark(affair)
            newsRepository.updateBookmarkStatus(affair.id, true)
        }
        return !isCurrentlyBookmarked
    }

    suspend fun isBookmarked(affairId: String): Boolean {
        return bookmarkRepository.isBookmarked(affairId)
    }
}
