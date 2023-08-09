package com.alpertign.newsmvvm.presentation.news_detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.alpertign.newsmvvm.R
import com.alpertign.newsmvvm.databinding.ActivityNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding
    var articleUrl: String = ""
    companion object {
        private const val ARTICLE_URL = "article.url"
        fun newIntent(context: Context, articleUrl: String): Intent {
            return Intent(
                context,
                NewsDetailActivity::class.java
            ).apply {
                putExtra(
                    ARTICLE_URL,
                    articleUrl
                )
            }

        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleUrl = intent.getStringExtra(ARTICLE_URL) ?: ""

        binding.apply {
            icBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            wvNewsDetail.settings.javaScriptEnabled = true
            wvNewsDetail.webViewClient = WebViewClient()
            wvNewsDetail.loadUrl(articleUrl)
        }
    }
}