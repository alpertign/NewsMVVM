package com.alpertign.newsmvvm.domain.use_cases

import com.alpertign.newsmvvm.domain.use_cases.get_articles_by_date.GetArticlesByDateUseCase
import com.alpertign.newsmvvm.domain.use_cases.get_current_articles.GetCurrentArticlesUseCase
import com.alpertign.newsmvvm.domain.use_cases.get_selected_article.GetSelectedArticleUseCase

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
data class UseCases(
    val getArticlesByDateUseCase: GetArticlesByDateUseCase,
    val getCurrentArticlesUseCase: GetCurrentArticlesUseCase,
    val getSelectedArticleUseCase: GetSelectedArticleUseCase
)