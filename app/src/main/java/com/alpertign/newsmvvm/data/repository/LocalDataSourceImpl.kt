package com.alpertign.newsmvvm.data.repository

import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.repository.LocalDataSource

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class LocalDataSourceImpl(newsDatabase: NewsDatabase): LocalDataSource {
    private val newsDao = newsDatabase.newsDao()
    override suspend fun getSelectedArticle(articleId: Int): Article {
        return newsDao.getSelectedArticle(articleId)
    }
}