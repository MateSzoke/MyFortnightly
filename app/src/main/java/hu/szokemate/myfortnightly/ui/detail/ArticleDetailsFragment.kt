package hu.szokemate.myfortnightly.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import hu.szokemate.myfortnightly.R
import hu.szokemate.myfortnightly.databinding.FragmentArticleDetailsBinding

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    private val viewModel: ArticleDetailsViewModel by viewModels()
    private var _binding: FragmentArticleDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = viewModel.article
        if (article != null) {
            Glide.with(requireContext()).load(article.urlToImage).into(binding.articleImage)
            binding.titleText.text = article.title
            binding.descriptionText.text = article.description
            binding.articleImage.isVisible = article.urlToImage.isNotBlank()
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}