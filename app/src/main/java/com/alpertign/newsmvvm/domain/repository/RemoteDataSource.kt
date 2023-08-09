package com.alpertign.newsmvvm.domain.repository

import com.alpertign.newsmvvm.domain.model.ApiResponse
import com.alpertign.newsmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
interface RemoteDataSource {
    suspend fun getArticlesByDate(fromDate: String, toDate: String): Response<ApiResponse>
}