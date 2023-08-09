package com.alpertign.newsmvvm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alpertign.newsmvvm.util.Constants.NEWS_DATABASE_TABLE
import kotlinx.serialization.Serializable

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@Serializable
@Entity(tableName = NEWS_DATABASE_TABLE)
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val source: ArticleSource? = null,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = ""
)