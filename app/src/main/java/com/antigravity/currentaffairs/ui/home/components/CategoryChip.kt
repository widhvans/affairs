package com.antigravity.currentaffairs.ui.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antigravity.currentaffairs.data.model.Category
import com.antigravity.currentaffairs.data.model.Language

@Composable
fun CategoryChip(
    category: Category?,
    label: String,
    isSelected: Boolean,
    count: Int? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chipColor = category?.let { Color(it.color) } ?: MaterialTheme.colorScheme.primary

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = if (count != null && count > 0) "$label ($count)" else label,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = chipColor.copy(alpha = 0.15f),
            selectedLabelColor = chipColor,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = if (isSelected) chipColor else MaterialTheme.colorScheme.outline,
            selectedBorderColor = chipColor,
            enabled = true,
            selected = isSelected
        ),
        modifier = modifier
    )
}

@Composable
fun CategoryChipRow(
    selectedCategory: Category?,
    categoryCounts: Map<Category, Int>,
    language: Language,
    onCategorySelected: (Category?) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // "All" chip
        CategoryChip(
            category = null,
            label = if (language == Language.ENGLISH) "All" else "सभी",
            isSelected = selectedCategory == null,
            count = categoryCounts.values.sum(),
            onClick = { onCategorySelected(null) }
        )

        // Category chips
        Category.entries.forEach { category ->
            val count = categoryCounts[category] ?: 0
            if (count > 0) {
                CategoryChip(
                    category = category,
                    label = category.getDisplayName(language),
                    isSelected = selectedCategory == category,
                    count = count,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }
}
