package com.alpertign.newsmvvm.data.repository

import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class LocalDataSourceImpl(newsDatabase: NewsDatabase): LocalDataSource {
    private val newsDao = newsDatabase.newsDao()

    override fun getAllArticlesFromDatabase(): Flow<List<Article>> {
        return newsDao.getAllArticlesFromDatabase()
    }

    override suspend fun insertArticlesToDatabase(articles: List<Article>) {
        return newsDao.insertArticlesToDatabase(articles)
    }
}