package hu.szokemate.myfortnightly.viewmodel

import hu.szokemate.myfortnightly.data.domain.repository.NewsRepository
import hu.szokemate.myfortnightly.mock.MockArticles.MOCK_ARTICLES
import hu.szokemate.myfortnightly.ui.articles.ArticlesViewModel
import io.kotest.core.spec.style.AnnotationSpec
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ArticlesViewModelTest : AnnotationSpec() {

    private lateinit var viewModel: ArticlesViewModel

    private val newsRepository: NewsRepository = mockk(relaxed = true) {
        coEvery { getArticles() } returns MOCK_ARTICLES
    }

    init {
        listener(ViewModelTestListener())
    }

    @Before
    fun setUp() {
        clearAllMocks()
        viewModel = ArticlesViewModel(newsRepository)
    }

    @Test
    fun `When viewModel getArticles called, Then repository getArticles called once`() {
        coVerify(exactly = 1) {
            viewModel.getArticles()
            newsRepository.getArticles()
        }
    }

    @Test
    fun `When viewModel getArticles called, Then result inserted to viewModel's articles`() {
        coVerify {
            viewModel.getArticles()
            viewModel.articles.value = MOCK_ARTICLES
        }
    }

    @Test
    fun `When viewModel openArticle called with an article, the repository openedArticle setter called`() {
        viewModel.openArticle(MOCK_ARTICLES.first())

        verify { newsRepository.openedArticle = MOCK_ARTICLES.first() }
    }
}