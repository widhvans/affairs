package com.antigravity.currentaffairs.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.antigravity.currentaffairs.data.model.Language
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val KEY_LANGUAGE = stringPreferencesKey("language")
        private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        private val KEY_NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        private val KEY_NOTIFICATION_HOUR = intPreferencesKey("notification_hour")
        private val KEY_NOTIFICATION_MINUTE = intPreferencesKey("notification_minute")
        private val KEY_LAST_FETCH_DATE = stringPreferencesKey("last_fetch_date")
        private val KEY_ONBOARDING_COMPLETE = booleanPreferencesKey("onboarding_complete")
    }

    val languageFlow: Flow<Language> = context.dataStore.data.map { preferences ->
        val code = preferences[KEY_LANGUAGE] ?: Language.ENGLISH.code
        Language.entries.find { it.code == code } ?: Language.ENGLISH
    }

    val darkModeFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_DARK_MODE] ?: false
    }

    val notificationsEnabledFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_NOTIFICATIONS_ENABLED] ?: true
    }

    val notificationHourFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_NOTIFICATION_HOUR] ?: 7
    }

    val notificationMinuteFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_NOTIFICATION_MINUTE] ?: 0
    }

    val lastFetchDateFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[KEY_LAST_FETCH_DATE]
    }

    suspend fun setLanguage(language: Language) {
        context.dataStore.edit { preferences ->
            preferences[KEY_LANGUAGE] = language.code
        }
    }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_DARK_MODE] = enabled
        }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_NOTIFICATIONS_ENABLED] = enabled
        }
    }

    suspend fun setNotificationTime(hour: Int, minute: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_NOTIFICATION_HOUR] = hour
            preferences[KEY_NOTIFICATION_MINUTE] = minute
        }
    }

    suspend fun setLastFetchDate(date: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_LAST_FETCH_DATE] = date
        }
    }
}
