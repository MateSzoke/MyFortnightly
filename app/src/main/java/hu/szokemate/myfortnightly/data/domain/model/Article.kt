package hu.szokemate.myfortnightly.data.domain.model

data class Article(
    val source: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val urlToImage: String,
)