package com.alpertign.newsmvvm.data.repository

import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.repository.LocalDataSource
import com.alpertign.newsmvvm.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {
    suspend fun getArticlesByDate(fromDate: String, toDate: String): Flow<List<Article>>{
        return remote.getArticlesByDate(fromDate, toDate)
    }

    suspend fun getSelectedArticle(articleId: Int): Article {
        return local.getSelectedArticle(articleId)
    }
}