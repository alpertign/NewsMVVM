package com.alpertign.newsmvvm.data.remote

import com.alpertign.newsmvvm.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
interface NewsApi {

    @GET("/everything")
    suspend fun getCurrentArticles(
        @Query("from") fromDate: String = "",
        @Query("to") toDate: String = "",
    ): ApiResponse


    @GET("/everything")
    suspend fun getArticlesByDate(
        @Query("from") fromDate: String = "",
        @Query("to") toDate: String = "",
    ): ApiResponse
}