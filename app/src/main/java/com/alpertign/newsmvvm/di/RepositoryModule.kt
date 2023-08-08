package com.alpertign.newsmvvm.di

import com.alpertign.newsmvvm.data.repository.Repository
import com.alpertign.newsmvvm.domain.use_cases.UseCases
import com.alpertign.newsmvvm.domain.use_cases.get_articles_by_date.GetArticlesByDateUseCase
import com.alpertign.newsmvvm.domain.use_cases.get_current_articles.GetCurrentArticlesUseCase
import com.alpertign.newsmvvm.domain.use_cases.get_selected_article.GetSelectedArticleUseCase

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
object RepositoryModule {
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            getArticlesByDateUseCase = GetArticlesByDateUseCase(repository),
            getCurrentArticlesUseCase = GetCurrentArticlesUseCase(repository),
            getSelectedArticleUseCase = GetSelectedArticleUseCase(repository)
        )
    }
}