package hu.szokemate.myfortnightly.data.network.model

import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class NetworkArticle(
    val source: NetworkSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: ZonedDateTime?,
    val content: String?,
)