package com.alpertign.newsmvvm.domain.repository

import com.alpertign.newsmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
interface RemoteDataSource {
    suspend fun getCurrentArticles(fromDate: String, toDate: String): Flow<List<Article>>
    suspend fun getArticlesByDate(fromDate: String, toDate: String): Flow<List<Article>>
}