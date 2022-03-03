package hu.szokemate.myfortnightly.ui.articles

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hu.szokemate.myfortnightly.data.domain.model.Article
import hu.szokemate.myfortnightly.databinding.ItemArticleBinding

class ArticlesAdapter : ListAdapter<Article, ArticlesAdapter.ViewHolder>(ArticleComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        private val context: Context = binding.root.context
        private val articleContainer: ViewGroup = binding.articleContainer
        private val publishedAtText: TextView = binding.publishedAtText
        private val firstArticleImage: ImageView = binding.firstArticleImage
        private val articleImage: ImageView = binding.articleImage
        private val titleText: TextView = binding.titleText
        private var article: Article? = null

        init {
            articleContainer.setOnClickListener {
                article?.let { article -> listener?.onArticleClicked(article) }
            }
        }

        fun bind(item: Article) {
            article = item

            publishedAtText.text = item.publishedAt
            titleText.text = item.title

            if (absoluteAdapterPosition == FIRST_POSITION) {
                firstArticleImage.isVisible = true
                articleImage.isVisible = false

                Glide.with(context).load(item.urlToImage).into(firstArticleImage)
            } else {
                firstArticleImage.isVisible = false
                articleImage.isVisible = true

                Glide.with(context).load(item.urlToImage).apply(RequestOptions.centerCropTransform()).into(articleImage)
            }
        }
    }

    interface Listener {
        fun onArticleClicked(article: Article)
    }

    companion object {
        private const val FIRST_POSITION = 0

        object ArticleComparator : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}