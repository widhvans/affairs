package com.antigravity.currentaffairs.data.remote.rss

object RssFeedConfig {
    val allFeeds: List<RssSource> = listOf(
        // English Government Sources (High Priority)
        RssSource(
            name = "PIB India",
            url = "https://pib.gov.in/RssMain.aspx?ModId=6&Lang=1&Regid=3",
            language = "en",
            isGovernment = true,
            priority = 1
        ),
        RssSource(
            name = "All India Radio",
            url = "https://newsonair.gov.in/Main-News-rss.aspx",
            language = "en",
            isGovernment = true,
            priority = 1
        ),

        // English General Sources
        RssSource(
            name = "Google News India",
            url = "https://news.google.com/rss?hl=en-IN&gl=IN&ceid=IN:en",
            language = "en",
            isGovernment = false,
            priority = 2
        ),
        RssSource(
            name = "Google News India - Nation",
            url = "https://news.google.com/rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRFZ4ZERBU0FtVnVLQUFQAQ?hl=en-IN&gl=IN&ceid=IN:en",
            language = "en",
            isGovernment = false,
            priority = 2
        ),
        RssSource(
            name = "Google News India - Science",
            url = "https://news.google.com/rss/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRFp0Y1RjU0FtVnVHZ0pKVGlnQVAB?hl=en-IN&gl=IN&ceid=IN:en",
            language = "en",
            isGovernment = false,
            priority = 3
        ),
        RssSource(
            name = "Google News India - Business",
            url = "https://news.google.com/rss/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGx6TVdZU0FtVnVHZ0pKVGlnQVAB?hl=en-IN&gl=IN&ceid=IN:en",
            language = "en",
            isGovernment = false,
            priority = 3
        ),
        RssSource(
            name = "Google News India - Sports",
            url = "https://news.google.com/rss/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRFp1ZEdvU0FtVnVHZ0pKVGlnQVAB?hl=en-IN&gl=IN&ceid=IN:en",
            language = "en",
            isGovernment = false,
            priority = 3
        ),

        // Hindi Government Sources
        RssSource(
            name = "PIB India Hindi",
            url = "https://pib.gov.in/RssMain.aspx?ModId=6&Lang=2&Regid=3",
            language = "hi",
            isGovernment = true,
            priority = 1
        ),

        // Hindi General Sources
        RssSource(
            name = "Google News India Hindi",
            url = "https://news.google.com/rss?hl=hi&gl=IN&ceid=IN:hi",
            language = "hi",
            isGovernment = false,
            priority = 2
        )
    )

    fun getEnglishFeeds(): List<RssSource> =
        allFeeds.filter { it.language == "en" }.sortedBy { it.priority }

    fun getHindiFeeds(): List<RssSource> =
        allFeeds.filter { it.language == "hi" }.sortedBy { it.priority }

    fun getGovernmentFeeds(): List<RssSource> =
        allFeeds.filter { it.isGovernment }.sortedBy { it.priority }
}
