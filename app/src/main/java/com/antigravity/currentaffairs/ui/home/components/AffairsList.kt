package com.antigravity.currentaffairs.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.Language

@Composable
fun AffairsList(
    affairs: List<CurrentAffair>,
    language: Language,
    onCardClick: (String) -> Unit,
    onBookmarkClick: (CurrentAffair) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = affairs,
            key = { _, affair -> affair.id }
        ) { index, affair ->
            AffairCard(
                affair = affair,
                language = language,
                index = index,
                onCardClick = { onCardClick(affair.id) },
                onBookmarkClick = { onBookmarkClick(affair) }
            )
        }
    }
}
