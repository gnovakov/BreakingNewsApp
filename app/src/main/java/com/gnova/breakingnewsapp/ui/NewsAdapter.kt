package com.gnova.breakingnewsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.databinding.ArticlePreviewItemBinding
import com.gnova.domain.models.Article
import com.squareup.picasso.Picasso

class NewsAdapter(
        private val onClickListener: OnClickListener
        ) : ListAdapter<Article, NewsAdapter.ArticleViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ArticlePreviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articles = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(articles)
        }
        holder.bind(articles)
    }

    class ArticleViewHolder(val binding: ArticlePreviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {



            binding.titleTv.text = article.title
            binding.descriptionTv.text = article.description
            binding.publishedAtTv.text = article.publishedAt.replaceUnusedChars("T", " ", "Z", "")
            binding.sourceTv.text = article.source

            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(binding.articleImageIv)
        }

    }



    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (article: Article) -> Unit) {
        fun onClick(article: Article) = clickListener(article)
    }

}





