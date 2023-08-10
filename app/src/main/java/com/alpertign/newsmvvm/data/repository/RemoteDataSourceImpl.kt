package com.alpertign.newsmvvm.data.repository

import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.data.remote.NewsApi
import com.alpertign.newsmvvm.domain.model.ApiResponse
import com.alpertign.newsmvvm.domain.repository.RemoteDataSource
import retrofit2.Response

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class RemoteDataSourceImpl(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
): RemoteDataSource {
    override suspend fun getArticlesByDate(fromDate: String, toDate: String): Response<ApiResponse> {
        return newsApi.getArticlesByDate(fromDate, toDate)
    }

}