package com.alpertign.newsmvvm.data.repository

import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.data.remote.NewsApi
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class RemoteDataSourceImpl(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
): RemoteDataSource {
    override fun getCurrentArticles(fromDate: String, toDate: String): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override fun getArticlesByDate(fromDate: String, toDate: String): Flow<List<Article>> {
        TODO("Not yet implemented")
    }


}