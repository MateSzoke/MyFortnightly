package hu.szokemate.myfortnightly.data.domain.repository

import hu.szokemate.myfortnightly.data.domain.model.Article
import hu.szokemate.myfortnightly.data.network.api.NewsApi
import hu.szokemate.myfortnightly.data.network.model.NetworkArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

    var openedArticle: Article? = null

    suspend fun getArticles(): List<Article>? = withContext(Dispatchers.IO) {
        val response = newsApi.getTopNews()
        if (response.isSuccessful) {
            response.body()?.articles?.mapIndexed { index, article -> article.toDomainModel(index) }
        } else null
    }

    private fun NetworkArticle.toDomainModel(index: Int): Article {
        return Article(
            id = index,
            source = source?.name ?: "",
            title = title ?: "",
            description = description ?: "",
            urlToImage = urlToImage ?: "",
            publishedAt = publishedAt?.let { getPublishedAtText(it) } ?: ""
        )
    }

    private fun getPublishedAtText(time: OffsetDateTime): String {
        val currentHour = OffsetDateTime.now().hour
        return "${currentHour - time.hour}H"
    }
}