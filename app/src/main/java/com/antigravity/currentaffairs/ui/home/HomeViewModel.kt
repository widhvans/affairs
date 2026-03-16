package com.antigravity.currentaffairs.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antigravity.currentaffairs.data.local.PreferenceManager
import com.antigravity.currentaffairs.data.model.Category
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.Language
import com.antigravity.currentaffairs.domain.usecase.BookmarkUseCase
import com.antigravity.currentaffairs.domain.usecase.FetchDailyAffairsUseCase
import com.antigravity.currentaffairs.domain.usecase.GetAffairsByDateUseCase
import com.antigravity.currentaffairs.utils.DateUtils
import com.antigravity.currentaffairs.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class HomeUiState(
    val affairs: List<CurrentAffair> = emptyList(),
    val filteredAffairs: List<CurrentAffair> = emptyList(),
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val isOffline: Boolean = false,
    val language: Language = Language.ENGLISH,
    val selectedCategory: Category? = null,
    val dateString: String = "",
    val formattedDate: String = "",
    val categoryCounts: Map<Category, Int> = emptyMap()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchDailyAffairsUseCase: FetchDailyAffairsUseCase,
    private val getAffairsByDateUseCase: GetAffairsByDateUseCase,
    private val bookmarkUseCase: BookmarkUseCase,
    private val preferenceManager: PreferenceManager,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeLanguage()
        loadAffairs()
    }

    private fun observeLanguage() {
        viewModelScope.launch {
            preferenceManager.languageFlow.collectLatest { language ->
                _uiState.update { state ->
                    val previousDay = DateUtils.getPreviousDay()
                    state.copy(
                        language = language,
                        formattedDate = DateUtils.formatDate(previousDay, language)
                    )
                }
            }
        }
    }

    fun loadAffairs() {
        viewModelScope.launch {
            val previousDay = DateUtils.getPreviousDay()
            val dateStr = previousDay.format(DateTimeFormatter.ISO_LOCAL_DATE)

            _uiState.update { it.copy(isLoading = true, error = null, dateString = dateStr) }

            if (!networkUtils.isNetworkAvailable()) {
                _uiState.update { it.copy(isOffline = true) }
            }

            // Try to load from cache first
            getAffairsByDateUseCase(dateStr).collectLatest { cachedAffairs ->
                if (cachedAffairs.isNotEmpty()) {
                    updateAffairs(cachedAffairs)
                    _uiState.update { it.copy(isLoading = false) }
                    return@collectLatest
                }

                // If no cache and we have network, fetch
                if (networkUtils.isNetworkAvailable()) {
                    fetchFromNetwork()
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "No internet connection and no cached data",
                            isOffline = true
                        )
                    }
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            fetchFromNetwork()
            _uiState.update { it.copy(isRefreshing = false) }
        }
    }

    private suspend fun fetchFromNetwork() {
        val result = fetchDailyAffairsUseCase()
        result.fold(
            onSuccess = { affairs ->
                updateAffairs(affairs)
                _uiState.update { it.copy(isLoading = false, error = null, isOffline = false) }
            },
            onFailure = { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to fetch current affairs"
                    )
                }
            }
        )
    }

    private fun updateAffairs(affairs: List<CurrentAffair>) {
        val categoryCounts = affairs.groupBy { it.category }
            .mapValues { it.value.size }

        val filtered = _uiState.value.selectedCategory?.let { category ->
            affairs.filter { it.category == category }
        } ?: affairs

        _uiState.update {
            it.copy(
                affairs = affairs,
                filteredAffairs = filtered,
                categoryCounts = categoryCounts
            )
        }
    }

    fun selectCategory(category: Category?) {
        val filtered = category?.let { cat ->
            _uiState.value.affairs.filter { it.category == cat }
        } ?: _uiState.value.affairs

        _uiState.update {
            it.copy(
                selectedCategory = category,
                filteredAffairs = filtered
            )
        }
    }

    fun toggleLanguage() {
        viewModelScope.launch {
            val newLanguage = when (_uiState.value.language) {
                Language.ENGLISH -> Language.HINDI
                Language.HINDI -> Language.ENGLISH
            }
            preferenceManager.setLanguage(newLanguage)
        }
    }

    fun toggleBookmark(affair: CurrentAffair) {
        viewModelScope.launch {
            val newBookmarkState = bookmarkUseCase.toggleBookmark(affair)
            val updatedAffairs = _uiState.value.affairs.map {
                if (it.id == affair.id) it.copy(isBookmarked = newBookmarkState) else it
            }
            updateAffairs(updatedAffairs)
        }
    }
}
