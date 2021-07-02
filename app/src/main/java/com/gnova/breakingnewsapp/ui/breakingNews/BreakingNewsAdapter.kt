package com.gnova.breakingnewsapp.ui.breakingNews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gnova.breakingnewsapp.R
import com.gnova.breakingnewsapp.databinding.ArticlePreviewItemBinding
import com.gnova.domain.models.Article

class BreakingNewsAdapter(
        //private val onClickListener: OnClickListener
        ) : ListAdapter<Article, BreakingNewsAdapter.ArticleViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ArticlePreviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder( holder: ArticleViewHolder, position: Int) {
        val articles = getItem(position)
        holder.itemView.setOnClickListener {
            //onClickListener.onClick(articles)
        }
        holder.bind(articles)
    }

    class ArticleViewHolder(val binding: ArticlePreviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {

            binding.titleTv.text = article.title
            binding.descriptionTv.text = article.description
            binding.publishedAtTv.text = article.publishedAt
            binding.sourceTv.text = article.source


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