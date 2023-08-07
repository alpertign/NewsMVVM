package com.alpertign.newsmvvm.presentation.news_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alpertign.newsmvvm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}