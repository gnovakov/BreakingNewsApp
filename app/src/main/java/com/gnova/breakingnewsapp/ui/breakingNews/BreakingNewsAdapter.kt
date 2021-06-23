package com.gnova.breakingnewsapp.ui.breakingNews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gnova.breakingnewsapp.R
import com.gnova.domain.models.Article
import com.squareup.picasso.Picasso

class BreakingNewsAdapter(
        private val onClickListener: OnClickListener
        ) : ListAdapter<Article, BreakingNewsAdapter.ArticleHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_preview_item, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val articles = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(articles)
        }
        holder.bind(articles)
    }

    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {

            }

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