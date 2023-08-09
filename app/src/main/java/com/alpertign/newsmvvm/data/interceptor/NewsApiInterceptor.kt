package com.alpertign.newsmvvm.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class NewsApiInterceptor(private val apiKey: String, private val sortingType: String, private val queryString: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("API_KEY", apiKey)
            .addQueryParameter("sortBy",sortingType)
            .addQueryParameter("q",queryString)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }
}