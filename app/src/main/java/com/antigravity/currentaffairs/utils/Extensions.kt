package com.antigravity.currentaffairs.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.antigravity.currentaffairs.data.model.CurrentAffair
import com.antigravity.currentaffairs.data.model.Language

fun CurrentAffair.getTitle(language: Language): String {
    return when (language) {
        Language.ENGLISH -> titleEnglish
        Language.HINDI -> titleHindi.ifBlank { titleEnglish }
    }
}

fun CurrentAffair.getKeyPoints(language: Language): List<String> {
    return when (language) {
        Language.ENGLISH -> keyPointsEnglish
        Language.HINDI -> keyPointsHindi.ifEmpty { keyPointsEnglish }
    }
}

fun Context.shareAffair(affair: CurrentAffair, language: Language) {
    val title = affair.getTitle(language)
    val points = affair.getKeyPoints(language)
    val pointsText = points.joinToString("\n") { "• $it" }

    val shareText = """
        |$title
        |
        |Key Points:
        |$pointsText
        |
        |Read more on AntiGravity App
        |${Constants.SHARE_HASHTAGS}
    """.trimMargin()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
        putExtra(Intent.EXTRA_SUBJECT, title)
    }
    startActivity(Intent.createChooser(intent, "Share via"))
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
