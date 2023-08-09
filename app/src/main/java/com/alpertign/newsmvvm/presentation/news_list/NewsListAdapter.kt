package com.alpertign.newsmvvm.presentation.news_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alpertign.newsmvvm.databinding.ItemNewsBinding
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.util.ArticlesDiffUtil
import javax.inject.Inject

/**
 * Created by Alperen Acikgoz on 09,August,2023
 */
class NewsListAdapter @Inject constructor() : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    private var articles = emptyList<Article>()
    var callBack: NewsListAdapterCallBack? = null
    interface NewsListAdapterCallBack {
        fun onclickArticle(article: Article)
    }
    class NewsListViewHolder(private val binding: ItemNewsBinding, private val callBack: NewsListAdapterCallBack) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.itemCardNews.setOnClickListener { callBack.onclickArticle(article) }
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup,callBack: NewsListAdapterCallBack): NewsListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return NewsListViewHolder(binding,callBack)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder.from(parent,callBack!!)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val currentRecipe = articles[position]
        holder.bind(currentRecipe)


    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(newData: List<Article>) {
        val articlesDiffUtil =
            ArticlesDiffUtil(
                articles,
                newData
            )
        val diffUtilResult = DiffUtil.calculateDiff(articlesDiffUtil)
        articles = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}