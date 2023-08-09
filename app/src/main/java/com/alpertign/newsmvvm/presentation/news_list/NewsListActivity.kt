package com.alpertign.newsmvvm.presentation.news_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.alpertign.newsmvvm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {

    private val vm: NewsListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
    }

}