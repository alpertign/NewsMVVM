package com.alpertign.newsmvvm.domain.repository

import com.alpertign.newsmvvm.domain.model.Article

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
interface LocalDataSource {
    suspend fun getSelectedArticle(articleId: Int): Article

}