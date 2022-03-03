package hu.szokemate.myfortnightly.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.szokemate.myfortnightly.data.domain.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {

    val article = newsRepository.openedArticle
}