package com.alpertign.newsmvvm.domain.model

import kotlinx.serialization.Serializable

/**
 * Created by Alperen Acikgoz on 09,August,2023
 */
@Serializable
data class ArticleSource (
    val id : String? = null,
    val name: String? = ""
)