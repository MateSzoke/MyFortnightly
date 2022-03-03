package hu.szokemate.myfortnightly.data.domain.repository

import androidx.annotation.VisibleForTesting
import hu.szokemate.myfortnightly.data.domain.model.Article
import hu.szokemate.myfortnightly.data.network.api.NewsApi
import hu.szokemate.myfortnightly.data.network.model.NetworkArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

    var openedArticle: Article? = null

    suspend fun getArticles(): List<Article>? = withContext(Dispatchers.IO) {
        try {
            val response = newsApi.getTopNews()
            response.body()?.articles?.mapIndexed { index, article -> article.toDomainModel(index) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @VisibleForTesting
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

    private fun getPublishedAtText(publishTime: ZonedDateTime): String {
        val duration = Duration.between(publishTime, ZonedDateTime.now()).toHours()
        return if (duration == 0L) "NOW" else "${duration}H"
    }
}