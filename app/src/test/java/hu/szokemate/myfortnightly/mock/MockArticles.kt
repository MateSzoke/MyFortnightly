package hu.szokemate.myfortnightly.mock

import hu.szokemate.myfortnightly.data.domain.model.Article
import hu.szokemate.myfortnightly.data.network.model.ArticlesResponse
import hu.szokemate.myfortnightly.data.network.model.NetworkArticle
import hu.szokemate.myfortnightly.data.network.model.NetworkSource
import java.time.ZoneId
import java.time.ZonedDateTime

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

    val MOCK_TOP_NEWS_RESPONSE = ArticlesResponse(
        status = "200",
        totalResults = 3,
        articles = listOf(
            NetworkArticle(
                title = "Mock article title",
                publishedAt = ZonedDateTime.of(2022, 3, 3, 11, 10, 2, 2, ZoneId.systemDefault()),
                description = "Mock article description",
                source = NetworkSource("1", "CNN"),
                urlToImage = "https://picsum.photos/200/300",
                author = "Test Author",
                content = "test content",
                url = null
            ),
            NetworkArticle(
                title = "Mock article title",
                publishedAt = ZonedDateTime.of(2022, 3, 3, 15, 10, 2, 2, ZoneId.systemDefault()),
                description = "Mock article description",
                source = NetworkSource("1", "CNN"),
                urlToImage = "https://picsum.photos/200/300",
                author = "Test Author",
                content = "test content",
                url = null
            ),
            NetworkArticle(
                title = "Mock article title",
                publishedAt = ZonedDateTime.of(2022, 3, 3, 18, 10, 2, 2, ZoneId.systemDefault()),
                description = "Mock article description",
                source = NetworkSource("1", "CNN"),
                urlToImage = "https://picsum.photos/200/300",
                author = "Test Author",
                content = "test content",
                url = null
            )
        )
    )
}