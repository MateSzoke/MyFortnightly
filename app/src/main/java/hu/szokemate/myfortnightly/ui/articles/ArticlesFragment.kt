package hu.szokemate.myfortnightly.ui.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.szokemate.myfortnightly.R
import hu.szokemate.myfortnightly.databinding.FragmentArticlesBinding

@AndroidEntryPoint
class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    private val viewModel: ArticlesViewModel by viewModels()
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentArticlesBinding.inflate(layoutInflater)

        viewModel.articles.observe(this, { articles ->
            binding.testText.text = articles.toString()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}