package com.alpertign.newsmvvm.domain.use_cases

import com.alpertign.newsmvvm.domain.use_cases.get_all_articles_from_database.GetAllArticlesFromDatabaseUseCase
import com.alpertign.newsmvvm.domain.use_cases.get_articles_by_date.GetArticlesByDateUseCase
import com.alpertign.newsmvvm.domain.use_cases.insert_articles_to_database.InsertArticlesToDatabaseUseCase

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
data class UseCases(
    val getArticlesByDateUseCase: GetArticlesByDateUseCase,
    val insertArticlesToDatabaseUseCase: InsertArticlesToDatabaseUseCase,
    val getAllArticlesFromDatabaseUseCase: GetAllArticlesFromDatabaseUseCase,
)