package com.alpertign.newsmvvm.di

import android.content.Context
import androidx.room.Room
import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.data.repository.LocalDataSourceImpl
import com.alpertign.newsmvvm.domain.repository.LocalDataSource
import com.alpertign.newsmvvm.util.Constants.NEWS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            NEWS_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        dataBase : NewsDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            newsDatabase = dataBase
        )
    }
}