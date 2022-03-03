package hu.szokemate.myfortnightly.repository

import hu.szokemate.myfortnightly.data.domain.repository.NewsRepository
import hu.szokemate.myfortnightly.data.network.api.NewsApi
import hu.szokemate.myfortnightly.mock.MockArticles.MOCK_TOP_NEWS_RESPONSE
import io.kotest.core.spec.style.AnnotationSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewsRepositoryTest : AnnotationSpec() {

    private lateinit var newsRepository: NewsRepository

    private val newsApi: NewsApi = mockk(relaxed = true) {
        coEvery { getTopNews() } returns Response.success(MOCK_TOP_NEWS_RESPONSE)
    }

    @Before
    fun setUp() {
        newsRepository = NewsRepository(newsApi)
    }

    @Test
    fun `Given repository getArticles called, Then newsApi getTopNews called once`() {
        coVerify(exactly = 1) {
            newsRepository.getArticles()
            newsApi.getTopNews()
        }
    }
}