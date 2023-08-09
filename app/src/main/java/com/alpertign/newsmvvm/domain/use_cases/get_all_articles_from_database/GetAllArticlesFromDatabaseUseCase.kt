package com.alpertign.newsmvvm.domain.use_cases.get_all_articles_from_database

import com.alpertign.newsmvvm.data.repository.Repository
import com.alpertign.newsmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Alperen Acikgoz on 10,August,2023
 */
class GetAllArticlesFromDatabaseUseCase(
    private val repository: Repository
) {
    operator fun invoke() : Flow<List<Article>> {
        return repository.getAllArticlesFromDatabase()
    }
}