package com.antigravity.currentaffairs.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antigravity.currentaffairs.data.local.PreferenceManager
import com.antigravity.currentaffairs.data.model.Language
import com.antigravity.currentaffairs.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val language: Language = Language.ENGLISH,
    val isDarkMode: Boolean = false,
    val isNotificationsEnabled: Boolean = true,
    val notificationHour: Int = 7,
    val notificationMinute: Int = 0,
    val cacheCleared: Boolean = false
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceManager.languageFlow.collectLatest { lang ->
                _uiState.update { it.copy(language = lang) }
            }
        }
        viewModelScope.launch {
            preferenceManager.darkModeFlow.collectLatest { dark ->
                _uiState.update { it.copy(isDarkMode = dark) }
            }
        }
        viewModelScope.launch {
            preferenceManager.notificationsEnabledFlow.collectLatest { enabled ->
                _uiState.update { it.copy(isNotificationsEnabled = enabled) }
            }
        }
    }

    fun setLanguage(language: Language) {
        viewModelScope.launch {
            preferenceManager.setLanguage(language)
        }
    }

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setDarkMode(enabled)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setNotificationsEnabled(enabled)
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            newsRepository.cleanupOldData(0)
            _uiState.update { it.copy(cacheCleared = true) }
        }
    }
}
