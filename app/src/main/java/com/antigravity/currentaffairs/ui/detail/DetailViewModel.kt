package com.antigravity.currentaffairs.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antigravity.currentaffairs.data.local.PreferenceManager
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.Language
import com.antigravity.currentaffairs.domain.usecase.BookmarkUseCase
import com.antigravity.currentaffairs.domain.usecase.GetAffairsByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val affair: CurrentAffair? = null,
    val relatedAffairs: List<CurrentAffair> = emptyList(),
    val isLoading: Boolean = true,
    val language: Language = Language.ENGLISH
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAffairsByDateUseCase: GetAffairsByDateUseCase,
    private val bookmarkUseCase: BookmarkUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceManager.languageFlow.collectLatest { language ->
                _uiState.update { it.copy(language = language) }
            }
        }
    }

    fun loadAffair(affairId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val affair = getAffairsByDateUseCase.getById(affairId)
            if (affair != null) {
                // Load related affairs from same category
                val related = getAffairsByDateUseCase(affair.date)
                    .firstOrNull()
                    ?.filter { it.category == affair.category && it.id != affair.id }
                    ?.take(3)
                    ?: emptyList()

                _uiState.update {
                    it.copy(
                        affair = affair,
                        relatedAffairs = related,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            val current = _uiState.value.affair ?: return@launch
            val newState = bookmarkUseCase.toggleBookmark(current)
            _uiState.update {
                it.copy(affair = current.copy(isBookmarked = newState))
            }
        }
    }
}
