package com.alpertign.newsmvvm.data.repository

import android.util.Log
import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.data.remote.NewsApi
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class RemoteDataSourceImpl(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
): RemoteDataSource {
    override suspend fun getArticlesByDate(fromDate: String, toDate: String): Flow<List<Article>> {
        val response = newsApi.getArticlesByDate(fromDate, toDate)

        if (response.isSuccessful) {
            val articles = response.body()?.articles ?: emptyList()
            //newsDatabase.newsDao().addArticles(articles)
            return flow { emit(articles) }
        } else {
            Log.e("RemoteDataSourceImpl","Response is not successful. ResponseCode:${response.code()}, ResponseMessage: ${response.message()}")
            return flow { emit(emptyList()) }
        }
    }

}