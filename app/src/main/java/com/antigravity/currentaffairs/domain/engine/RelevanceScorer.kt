package com.antigravity.currentaffairs.domain.engine

import com.antigravity.currentaffairs.data.model.Category
import com.antigravity.currentaffairs.data.model.RawNewsItem
import com.antigravity.currentaffairs.utils.DateUtils
import java.time.ZoneOffset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RelevanceScorer @Inject constructor() {

    private val categoryKeywords = mapOf(
        Category.NATIONAL to listOf(
            "government", "ministry", "scheme", "policy", "bill",
            "parliament", "cabinet", "supreme court", "president",
            "prime minister", "sarkar", "yojana", "niti aayog",
            "lok sabha", "rajya sabha", "bharat", "india",
            "constitution", "amendment", "ordinance", "gazette"
        ),
        Category.INTERNATIONAL to listOf(
            "summit", "treaty", "un", "g20", "g7", "bilateral",
            "agreement", "world bank", "imf", "who", "nato",
            "united nations", "foreign", "diplomat", "embassy",
            "global", "multilateral", "brics"
        ),
        Category.ECONOMY to listOf(
            "gdp", "rbi", "inflation", "fiscal", "budget", "tax",
            "sebi", "stock", "rupee", "export", "import", "fdi",
            "economy", "economic", "financial", "bank", "reserve",
            "monetary", "revenue", "deficit", "surplus", "trade"
        ),
        Category.SCIENCE_TECH to listOf(
            "isro", "nasa", "satellite", "ai", "space",
            "research", "technology", "drdo", "missile", "5g",
            "science", "innovation", "digital", "cyber", "quantum",
            "launch", "rocket", "spacecraft", "laboratory"
        ),
        Category.SPORTS to listOf(
            "cricket", "olympics", "medal", "championship", "fifa",
            "world cup", "record", "tournament", "asian games",
            "hockey", "tennis", "badminton", "wrestling", "boxing",
            "athlete", "icc", "bcci", "commonwealth"
        ),
        Category.AWARDS to listOf(
            "award", "prize", "nobel", "padma", "bharat ratna",
            "honor", "recognition", "puraskar", "awarded",
            "lifetime achievement", "gallantry", "literary"
        ),
        Category.APPOINTMENTS to listOf(
            "appointed", "new chief", "governor", "chairman",
            "ceo", "director", "niyukti", "pad", "designate",
            "secretary", "commissioner", "head", "takes charge"
        ),
        Category.DEFENSE to listOf(
            "army", "navy", "air force", "exercise", "missile",
            "defense", "military", "sena", "raksha", "weapon",
            "border", "security", "combat", "drill", "fleet"
        ),
        Category.ENVIRONMENT to listOf(
            "climate", "wildlife", "forest", "pollution",
            "biodiversity", "conservation", "paryavaran",
            "green", "renewable", "solar", "emission", "carbon",
            "ecology", "species", "national park"
        ),
        Category.IMPORTANT_DAYS to listOf(
            "day", "week", "divas", "anniversary",
            "jayanti", "observation", "observe", "celebrated",
            "commemorat", "birth anniversary", "foundation day"
        )
    )

    private val importantKeywords = listOf(
        "india", "minister", "launched", "appointed", "signed",
        "agreement", "record", "first", "new", "billion",
        "crore", "scheme", "policy", "act", "amendment",
        "inaugurated", "approved", "commission", "committee",
        "report", "announcement", "landmark"
    )

    private val govSources = listOf(
        "pib", "gov.in", "nic.in", "rbi.org", "isro.gov",
        "mea.gov", "india.gov", "niti.gov", "moef.gov"
    )

    fun calculateScore(item: RawNewsItem): Int {
        var score = 0
        val titleLower = item.title.lowercase()
        val descLower = item.description.lowercase()
        val combined = "$titleLower $descLower"

        // Category matching: +20 per category hit
        categoryKeywords.forEach { (_, keywords) ->
            if (keywords.any { it in combined }) {
                score += 20
            }
        }

        // Government source bonus: +15
        if (govSources.any { it in item.sourceUrl.lowercase() }) {
            score += 15
        }
        if (govSources.any { it in item.sourceName.lowercase() }) {
            score += 10
        }

        // Important keyword density: +5 per keyword, max +25
        val keywordHits = importantKeywords.count { it in combined }
        score += minOf(keywordHits * 5, 25)

        // Recency bonus: +10 if from previous day
        val yesterday = DateUtils.getPreviousDay().atStartOfDay()
            .toInstant(ZoneOffset.UTC).toEpochMilli()
        if (item.publishedDate >= yesterday) {
            score += 10
        }

        // Title quality: prefer 5-20 words
        val wordCount = item.title.split(" ").size
        if (wordCount in 5..20) {
            score += 5
        }

        // Description quality bonus
        if (item.description.length > 100) {
            score += 5
        }

        return minOf(score, 100)
    }

    fun getCategoryKeywords(): Map<Category, List<String>> = categoryKeywords
}
