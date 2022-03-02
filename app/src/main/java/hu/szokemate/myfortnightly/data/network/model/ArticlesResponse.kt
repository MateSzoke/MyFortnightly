package hu.szokemate.myfortnightly.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NetworkArticle>
)