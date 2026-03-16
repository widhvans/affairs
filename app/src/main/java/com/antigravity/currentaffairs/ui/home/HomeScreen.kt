package com.antigravity.currentaffairs.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.antigravity.currentaffairs.data.model.Language
import com.antigravity.currentaffairs.ui.home.components.AffairsList
import com.antigravity.currentaffairs.ui.home.components.CategoryChipRow
import com.antigravity.currentaffairs.ui.home.components.DateSelector
import com.antigravity.currentaffairs.ui.home.components.ShimmerLoading
import com.antigravity.currentaffairs.ui.home.components.TopBar

@Composable
fun HomeScreen(
    onAffairClick: (String) -> Unit,
    onBookmarksClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        TopBar(
            language = uiState.language,
            onLanguageToggle = viewModel::toggleLanguage,
            onBookmarksClick = onBookmarksClick,
            onSettingsClick = onSettingsClick
        )

        // Offline banner
        if (uiState.isOffline) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.1f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (uiState.language == Language.ENGLISH)
                        "⚡ Offline Mode - Showing cached data"
                    else
                        "⚡ ऑफ़लाइन मोड - कैश्ड डेटा दिखा रहे हैं",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Date selector
        DateSelector(
            formattedDate = uiState.formattedDate,
            language = uiState.language,
            affairsCount = uiState.affairs.size
        )

        // Category chips
        CategoryChipRow(
            selectedCategory = uiState.selectedCategory,
            categoryCounts = uiState.categoryCounts,
            language = uiState.language,
            onCategorySelected = viewModel::selectCategory
        )

        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                uiState.isLoading -> {
                    ShimmerLoading()
                }

                uiState.error != null && uiState.affairs.isEmpty() -> {
                    ErrorState(
                        error = uiState.error ?: "",
                        isOffline = uiState.isOffline,
                        language = uiState.language,
                        onRetry = viewModel::loadAffairs
                    )
                }

                uiState.filteredAffairs.isEmpty() -> {
                    EmptyState(language = uiState.language)
                }

                else -> {
                    AffairsList(
                        affairs = uiState.filteredAffairs,
                        language = uiState.language,
                        onCardClick = onAffairClick,
                        onBookmarkClick = viewModel::toggleBookmark
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorState(
    error: String,
    isOffline: Boolean,
    language: Language,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Icon(
            imageVector = if (isOffline) Icons.Default.WifiOff else Icons.Default.ErrorOutline,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f),
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isOffline) {
                if (language == Language.ENGLISH) "No Internet Connection" else "इंटरनेट कनेक्शन नहीं है"
            } else {
                if (language == Language.ENGLISH) "Something went wrong" else "कुछ गलत हो गया"
            },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (language == Language.ENGLISH)
                "Please check your connection and try again"
            else
                "कृपया अपना कनेक्शन जाँचें और पुनः प्रयास करें",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = if (language == Language.ENGLISH) "Retry" else "पुनः प्रयास करें",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EmptyState(language: Language) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Inbox,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (language == Language.ENGLISH)
                "No current affairs available"
            else
                "कोई समसामयिक घटना उपलब्ध नहीं है",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (language == Language.ENGLISH)
                "Pull down to refresh or try again later"
            else
                "रिफ्रेश करने के लिए नीचे खींचें या बाद में पुनः प्रयास करें",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )
    }
}
