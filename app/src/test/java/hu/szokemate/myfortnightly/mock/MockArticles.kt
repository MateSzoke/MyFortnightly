package hu.szokemate.myfortnightly.mock

import hu.szokemate.myfortnightly.data.domain.model.Article

object MockArticles {
    val MOCK_ARTICLES = listOf(
        Article(
            id = 1,
            title = "Mock article title",
            publishedAt = "2H",
            description = "Mock article description",
            source = "CNN",
            urlToImage = "https://picsum.photos/200/300"
        ),
        Article(
            id = 2,
            title = "Mock article title",
            publishedAt = "2H",
            description = "Mock article description",
            source = "CNN",
            urlToImage = "https://picsum.photos/200/300"
        ),
        Article(
            id = 3,
            title = "Mock article title",
            publishedAt = "2H",
            description = "Mock article description",
            source = "CNN",
            urlToImage = "https://picsum.photos/200/300"
        ),
    )
}