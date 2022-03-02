package hu.szokemate.myfortnightly.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.szokemate.myfortnightly.R
import hu.szokemate.myfortnightly.data.domain.model.Article
import hu.szokemate.myfortnightly.databinding.FragmentArticlesBinding
import hu.szokemate.myfortnightly.ui.detail.ArticleDetailsFragment

@AndroidEntryPoint
class ArticlesFragment : Fragment(R.layout.fragment_articles), ArticlesAdapter.Listener {

    private val viewModel: ArticlesViewModel by viewModels()
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArticleList()

        viewModel.articles.observe(this, { articles ->
            articlesAdapter.submitList(articles)
        })
    }

    private fun setupArticleList() {
        articlesAdapter = ArticlesAdapter()
        articlesAdapter.listener = this
        binding.articleList.adapter = articlesAdapter
        binding.articleList.isVisible = true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onArticleClicked(article: Article) {
        viewModel.openArticle(article)
        val detailsFragment = ArticleDetailsFragment()
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, detailsFragment, detailsFragment.tag)
            .addToBackStack(detailsFragment.tag)
            .commit()
    }
}