package hu.szokemate.myfortnightly.viewmodel

import hu.szokemate.myfortnightly.data.domain.repository.NewsRepository
import hu.szokemate.myfortnightly.mock.MockArticles.MOCK_ARTICLES
import hu.szokemate.myfortnightly.ui.detail.ArticleDetailsViewModel
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ArticleDetailsViewModelTest : AnnotationSpec() {

    private lateinit var viewModel: ArticleDetailsViewModel

    private val newsRepository: NewsRepository = mockk(relaxed = true) {
        coEvery { openedArticle } returns MOCK_ARTICLES.first()
    }

    init {
        listener(ViewModelTestListener())
    }

    @Before
    fun setUp() {
        clearAllMocks()
        viewModel = ArticleDetailsViewModel(newsRepository)
    }

    @Test
    fun `viewModel article returns newsRepository article`() {
        viewModel.article shouldBe newsRepository.openedArticle
    }
}