package hu.szokemate.myfortnightly.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.szokemate.myfortnightly.data.domain.model.Article
import hu.szokemate.myfortnightly.data.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val articles: MutableLiveData<List<Article>> = MutableLiveData()

    init {
        getArticles()
    }

    private fun getArticles() = viewModelScope.launch {
        articles.value = newsRepository.getArticles()
    }
}