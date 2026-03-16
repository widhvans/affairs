package com.antigravity.currentaffairs.data.remote.rss

import com.antigravity.currentaffairs.data.model.RawNewsItem
import com.antigravity.currentaffairs.utils.TextUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RssFeedParser @Inject constructor() {

    companion object {
        private const val TIMEOUT_MS = 15000
        private val DATE_FORMATS = listOf(
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH),
            SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
        )
    }

    suspend fun parseRssFeed(source: RssSource): List<RawNewsItem> {
        return withContext(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect(source.url)
                    .timeout(TIMEOUT_MS)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .get()

                val items = doc.select("item, entry")
                items.mapNotNull { item ->
                    try {
                        parseItem(item, source)
                    } catch (e: Exception) {
                        null
                    }
                }.filter { it.title.isNotBlank() && it.title.length > 10 }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun parseMultipleFeeds(sources: List<RssSource>): List<RawNewsItem> {
        return withContext(Dispatchers.IO) {
            val allItems = mutableListOf<RawNewsItem>()
            for (source in sources) {
                try {
                    val items = parseRssFeed(source)
                    allItems.addAll(items)
                } catch (e: Exception) {
                    // Continue with next feed
                }
            }
            allItems
        }
    }

    private fun parseItem(item: Element, source: RssSource): RawNewsItem {
        val title = extractTitle(item)
        val description = extractDescription(item)
        val link = extractLink(item)
        val imageUrl = extractImageFromItem(item)
        val pubDate = extractPubDate(item)
        val category = item.select("category").text().takeIf { it.isNotBlank() }

        return RawNewsItem(
            title = TextUtils.cleanHtml(title),
            description = TextUtils.cleanHtml(description),
            sourceUrl = link,
            sourceName = source.name,
            imageUrl = imageUrl,
            publishedDate = pubDate,
            rawCategory = category
        )
    }

    private fun extractTitle(item: Element): String {
        return item.select("title").text().ifBlank {
            item.select("title").attr("value")
        }
    }

    private fun extractDescription(item: Element): String {
        val desc = item.select("description").text().ifBlank {
            item.select("summary").text().ifBlank {
                item.select("content").text().ifBlank {
                    item.select("content\\:encoded").text()
                }
            }
        }
        return desc
    }

    private fun extractLink(item: Element): String {
        return item.select("link[href]").attr("href").ifBlank {
            item.select("link").text().ifBlank {
                item.select("link").attr("href").ifBlank {
                    item.select("guid").text()
                }
            }
        }
    }

    private fun extractImageFromItem(item: Element): String? {
        // Check for media:content or media:thumbnail
        val mediaContent = item.select("media\\:content, media\\:thumbnail").attr("url")
        if (mediaContent.isNotBlank()) return mediaContent

        // Check enclosure for images
        val enclosure = item.select("enclosure[type^=image]").attr("url")
        if (enclosure.isNotBlank()) return enclosure

        // Try to extract image from description HTML
        val descHtml = item.select("description").html()
        val imgMatch = Regex("""<img[^>]+src="([^"]+)"""").find(descHtml)
        if (imgMatch != null) return imgMatch.groupValues[1]

        // Try image tag
        val imageTag = item.select("image url, image").text()
        if (imageTag.isNotBlank() && (imageTag.startsWith("http"))) return imageTag

        return null
    }

    private fun extractPubDate(item: Element): Long {
        val dateStr = item.select("pubDate").text().ifBlank {
            item.select("published").text().ifBlank {
                item.select("updated").text().ifBlank {
                    item.select("dc\\:date").text()
                }
            }
        }

        if (dateStr.isBlank()) return System.currentTimeMillis()

        for (format in DATE_FORMATS) {
            try {
                return format.parse(dateStr)?.time ?: continue
            } catch (e: Exception) {
                continue
            }
        }

        return System.currentTimeMillis()
    }
}
