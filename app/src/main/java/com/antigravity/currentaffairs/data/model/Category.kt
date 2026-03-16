package com.antigravity.currentaffairs.data.model

enum class Category(
    val displayNameEn: String,
    val displayNameHi: String,
    val iconName: String,
    val color: Long
) {
    NATIONAL("National", "राष्ट्रीय", "flag", 0xFF1565C0),
    INTERNATIONAL("International", "अंतर्राष्ट्रीय", "public", 0xFF2E7D32),
    ECONOMY("Economy", "अर्थव्यवस्था", "trending_up", 0xFFE65100),
    SCIENCE_TECH("Science & Tech", "विज्ञान एवं प्रौद्योगिकी", "science", 0xFF6A1B9A),
    SPORTS("Sports", "खेल", "sports_soccer", 0xFFC62828),
    AWARDS("Awards & Honors", "पुरस्कार एवं सम्मान", "emoji_events", 0xFFF9A825),
    APPOINTMENTS("Appointments", "नियुक्तियाँ", "person_add", 0xFF00838F),
    DEFENSE("Defense", "रक्षा", "shield", 0xFF4E342E),
    ENVIRONMENT("Environment", "पर्यावरण", "eco", 0xFF2E7D32),
    IMPORTANT_DAYS("Important Days", "महत्वपूर्ण दिवस", "calendar_today", 0xFFAD1457);

    fun getDisplayName(language: Language): String {
        return when (language) {
            Language.ENGLISH -> displayNameEn
            Language.HINDI -> displayNameHi
        }
    }
}
