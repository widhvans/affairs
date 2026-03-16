package com.antigravity.currentaffairs.data.remote.scraper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebScraper @Inject constructor() {

    companion object {
        private const val TIMEOUT_MS = 15000
        private const val USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    }

    suspend fun scrapeArticleContent(url: String): ArticleContent? {
        return withContext(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect(url)
                    .timeout(TIMEOUT_MS)
                    .userAgent(USER_AGENT)
                    .followRedirects(true)
                    .get()

                val title = doc.select("h1").first()?.text()
                    ?: doc.title()

                val paragraphs = doc.select("article p, .content p, .story-body p, .article-body p, main p")
                    .map { it.text() }
                    .filter { it.length > 30 }

                val content = if (paragraphs.isNotEmpty()) {
                    paragraphs.joinToString(". ")
                } else {
                    doc.select("p")
                        .map { it.text() }
                        .filter { it.length > 30 }
                        .take(10)
                        .joinToString(". ")
                }

                val imageUrl = ImageExtractor.extractOgImage(doc)
                    ?: ImageExtractor.extractFirstImage(doc)

                ArticleContent(
                    title = title,
                    content = content,
                    imageUrl = imageUrl,
                    url = url
                )
            } catch (e: Exception) {
                null
            }
        }
    }
}

data class ArticleContent(
    val title: String,
    val content: String,
    val imageUrl: String?,
    val url: String
)
