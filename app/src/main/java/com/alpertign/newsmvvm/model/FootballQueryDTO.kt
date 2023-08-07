package com.alpertign.newsmvvm.model

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
data class FootballQueryDTO (
    val status: String = "",
    val totalResults: Int = Int.MIN_VALUE,
    val articles: List<Article>
)