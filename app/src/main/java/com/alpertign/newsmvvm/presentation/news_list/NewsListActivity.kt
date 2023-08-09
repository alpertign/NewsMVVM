package com.alpertign.newsmvvm.presentation.news_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.alpertign.newsmvvm.databinding.ActivityNewsListBinding
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.presentation.news_detail.NewsDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsListBinding
    private val vm: NewsListViewModel by viewModels()

    @Inject
    lateinit var newsListAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeObservers()
        initializeAdapter()

        binding.apply {
            ivHearth.setOnClickListener {
                Toast.makeText(
                    this@NewsListActivity,
                    "Love you <3",
                    Toast.LENGTH_SHORT
                ).show() }

            ivFilter.setOnClickListener {

            }
        }
    }

    private fun initializeAdapter() {
        binding.apply {
            rvNews.layoutManager = GridLayoutManager(
                this@NewsListActivity,
                2,
                RecyclerView.VERTICAL,
                false
            )
            rvNews.adapter = newsListAdapter

            newsListAdapter.callBack = object : NewsListAdapter.NewsListAdapterCallBack {
                override fun onclickArticle(article: Article) {
                    //TODO("tıklayınca databaseden article i çek sonucunda webview i aç")
                    this@NewsListActivity.run {
                        startActivity(NewsDetailActivity.newIntent(this@NewsListActivity,article.url?: ""))
                    }
                }

            }

        }
    }

    private fun initializeObservers() {
        vm.articles.observe(this) {
            newsListAdapter.setData(it)
        }
    }
}