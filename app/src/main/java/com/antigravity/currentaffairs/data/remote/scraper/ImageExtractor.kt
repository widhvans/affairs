package com.antigravity.currentaffairs.data.remote.scraper

import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageExtractor @Inject constructor() {

    companion object {
        fun extractOgImage(doc: Document): String? {
            val ogImage = doc.select("meta[property=og:image]").attr("content")
            if (ogImage.isNotBlank() && ogImage.startsWith("http")) return ogImage

            val twitterImage = doc.select("meta[name=twitter:image]").attr("content")
            if (twitterImage.isNotBlank() && twitterImage.startsWith("http")) return twitterImage

            val twitterImageSrc = doc.select("meta[name=twitter:image:src]").attr("content")
            if (twitterImageSrc.isNotBlank() && twitterImageSrc.startsWith("http")) return twitterImageSrc

            return null
        }

        fun extractFirstImage(doc: Document): String? {
            val images = doc.select("article img, .content img, main img, img[src]")
            for (img in images) {
                val src = img.attr("abs:src").ifBlank { img.attr("src") }
                if (src.startsWith("http") && !isIconOrLogo(src)) {
                    return src
                }
            }
            return null
        }

        private fun isIconOrLogo(url: String): Boolean {
            val lower = url.lowercase()
            return lower.contains("logo") ||
                    lower.contains("icon") ||
                    lower.contains("favicon") ||
                    lower.contains("avatar") ||
                    lower.endsWith(".svg") ||
                    lower.contains("1x1") ||
                    lower.contains("blank") ||
                    lower.contains("pixel")
        }
    }

    fun extractImageFromUrl(url: String): String? {
        return try {
            val doc = org.jsoup.Jsoup.connect(url)
                .timeout(10000)
                .userAgent("Mozilla/5.0")
                .get()
            extractOgImage(doc) ?: extractFirstImage(doc)
        } catch (e: Exception) {
            null
        }
    }
}
