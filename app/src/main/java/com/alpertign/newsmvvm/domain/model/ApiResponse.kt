package com.alpertign.newsmvvm.domain.model

import kotlinx.serialization.Serializable

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@Serializable
data class ApiResponse (
    val status: String = "",
    val totalResults: Int = Int.MIN_VALUE,
    val articles: List<Article> = emptyList()
)