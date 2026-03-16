package com.antigravity.currentaffairs.domain.usecase

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslateUseCase @Inject constructor() {

    private val englishHindiTranslator by lazy {
        Translation.getClient(
            TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.HINDI)
                .build()
        )
    }

    private val hindiEnglishTranslator by lazy {
        Translation.getClient(
            TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.HINDI)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build()
        )
    }

    private var isModelDownloaded = false

    suspend fun ensureModelDownloaded() {
        if (isModelDownloaded) return
        withContext(Dispatchers.IO) {
            try {
                val conditions = DownloadConditions.Builder()
                    .requireWifi()
                    .build()
                englishHindiTranslator.downloadModelIfNeeded(conditions).await()
                hindiEnglishTranslator.downloadModelIfNeeded(conditions).await()
                isModelDownloaded = true
            } catch (e: Exception) {
                // Model download failed, translations will fallback
            }
        }
    }

    suspend fun translateToHindi(text: String): String {
        return withContext(Dispatchers.IO) {
            try {
                ensureModelDownloaded()
                englishHindiTranslator.translate(text).await()
            } catch (e: Exception) {
                text // Fallback to original text
            }
        }
    }

    suspend fun translateToEnglish(text: String): String {
        return withContext(Dispatchers.IO) {
            try {
                ensureModelDownloaded()
                hindiEnglishTranslator.translate(text).await()
            } catch (e: Exception) {
                text
            }
        }
    }

    fun close() {
        englishHindiTranslator.close()
        hindiEnglishTranslator.close()
    }
}
