package com.antigravity.currentaffairs.data.remote.api

object ApiConstants {
    // GNews API - Free tier: 100 requests/day
    const val GNEWS_BASE_URL = "https://gnews.io/api/v4/"
    const val GNEWS_API_KEY = "0b6cc1ad0f3973fd7dadf4fb8349f242"

    // Currents API - Free tier
    const val CURRENTS_BASE_URL = "https://api.currentsapi.services/v1/"
    const val CURRENTS_API_KEY = "JIIEECJCLvpnmTdB7eqT-zORlPG1mY2EstXKDV779Mwj17vU"

    // Timeouts
    const val CONNECT_TIMEOUT = 15L
    const val READ_TIMEOUT = 15L
    const val WRITE_TIMEOUT = 15L

    // RSS Feed URLs
    val RSS_FEEDS_ENGLISH = listOf(
        "https://news.google.com/rss?hl=en-IN&gl=IN&ceid=IN:en",
        "https://pib.gov.in/RssMain.aspx?ModId=6&Lang=1&Regid=3",
        "https://newsonair.gov.in/Main-News-rss.aspx"
    )

    val RSS_FEEDS_HINDI = listOf(
        "https://news.google.com/rss?hl=hi&gl=IN&ceid=IN:hi",
        "https://pib.gov.in/RssMain.aspx?ModId=6&Lang=2&Regid=3"
    )
}
