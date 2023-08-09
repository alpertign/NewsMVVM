package com.alpertign.newsmvvm.di

import com.alpertign.newsmvvm.data.interceptor.NewsApiInterceptor
import com.alpertign.newsmvvm.data.local.NewsDatabase
import com.alpertign.newsmvvm.data.remote.NewsApi
import com.alpertign.newsmvvm.data.repository.RemoteDataSourceImpl
import com.alpertign.newsmvvm.domain.repository.RemoteDataSource
import com.alpertign.newsmvvm.util.Constants.API_KEY
import com.alpertign.newsmvvm.util.Constants.BASE_URL
import com.alpertign.newsmvvm.util.Constants.QUERY_STRING
import com.alpertign.newsmvvm.util.Constants.SORTING_TYPE
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(
                15,
                TimeUnit.SECONDS
            )
            .connectTimeout(
                15,
                TimeUnit.SECONDS
            )
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        val contentType = "application/json".toMediaType()
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(
                NewsApiInterceptor(
                    API_KEY,
                    SORTING_TYPE,
                    QUERY_STRING
                )
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(Json.asConverterFactory(contentType = contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        newsApi: NewsApi,
        newsDatabase: NewsDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            newsApi = newsApi,
            newsDatabase = newsDatabase
        )
    }
}