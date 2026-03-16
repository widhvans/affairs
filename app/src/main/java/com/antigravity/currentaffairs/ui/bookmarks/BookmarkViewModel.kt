package com.antigravity.currentaffairs.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antigravity.currentaffairs.data.local.PreferenceManager
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.Language
import com.antigravity.currentaffairs.domain.usecase.BookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookmarkUiState(
    val bookmarks: List<CurrentAffair> = emptyList(),
    val searchQuery: String = "",
    val language: Language = Language.ENGLISH,
    val isLoading: Boolean = true
)

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkUseCase: BookmarkUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarkUiState())
    val uiState: StateFlow<BookmarkUiState> = _uiState.asStateFlow()

    init {
        observeLanguage()
        loadBookmarks()
    }

    private fun observeLanguage() {
        viewModelScope.launch {
            preferenceManager.languageFlow.collectLatest { language ->
                _uiState.update { it.copy(language = language) }
            }
        }
    }

    fun loadBookmarks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            bookmarkUseCase.getAllBookmarks().collectLatest { bookmarks ->
                _uiState.update {
                    it.copy(bookmarks = bookmarks, isLoading = false)
                }
            }
        }
    }

    fun search(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        viewModelScope.launch {
            if (query.isBlank()) {
                loadBookmarks()
            } else {
                bookmarkUseCase.searchBookmarks(query).collectLatest { results ->
                    _uiState.update { it.copy(bookmarks = results) }
                }
            }
        }
    }

    fun removeBookmark(affair: CurrentAffair) {
        viewModelScope.launch {
            bookmarkUseCase.toggleBookmark(affair)
            loadBookmarks()
        }
    }
}
