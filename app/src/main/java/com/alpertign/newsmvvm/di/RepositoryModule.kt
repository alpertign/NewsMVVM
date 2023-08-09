package com.alpertign.newsmvvm.di

import com.alpertign.newsmvvm.data.repository.Repository
import com.alpertign.newsmvvm.domain.use_cases.UseCases
import com.alpertign.newsmvvm.domain.use_cases.get_articles_by_date.GetArticlesByDateUseCase
import com.alpertign.newsmvvm.domain.use_cases.get_selected_article.GetSelectedArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            getArticlesByDateUseCase = GetArticlesByDateUseCase(repository),
            getSelectedArticleUseCase = GetSelectedArticleUseCase(repository)
        )
    }
}