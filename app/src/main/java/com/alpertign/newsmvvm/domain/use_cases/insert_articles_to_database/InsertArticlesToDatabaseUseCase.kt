package com.alpertign.newsmvvm.domain.use_cases.insert_articles_to_database

import com.alpertign.newsmvvm.data.repository.Repository
import com.alpertign.newsmvvm.domain.model.Article

/**
 * Created by Alperen Acikgoz on 10,August,2023
 */

class InsertArticlesToDatabaseUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(articles: List<Article>)  {
        repository.insertArticlesToDatabase(articles)
    }
}