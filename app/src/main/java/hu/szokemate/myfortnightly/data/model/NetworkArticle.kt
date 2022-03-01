package hu.szokemate.myfortnightly.data.model

import com.squareup.moshi.JsonClass
import java.time.OffsetDateTime

@JsonClass(generateAdapter = true)
data class NetworkArticle(
    val source: NetworkSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: OffsetDateTime?,
    val content: String?,
)