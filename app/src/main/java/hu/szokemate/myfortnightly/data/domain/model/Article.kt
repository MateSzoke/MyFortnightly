package hu.szokemate.myfortnightly.data.domain.model

import java.io.Serializable

data class Article(
    val id: Int,
    val source: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val urlToImage: String,
) : Serializable