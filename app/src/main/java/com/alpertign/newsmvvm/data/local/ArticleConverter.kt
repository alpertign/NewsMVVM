package com.alpertign.newsmvvm.data.local

import com.alpertign.newsmvvm.domain.model.ArticleSource
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Alperen Acikgoz on 09,August,2023
 */

class ArticleConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromArticleSource(articleSource: ArticleSource?): String? {
        return gson.toJson(articleSource)
    }
    @TypeConverter
    fun toArticleSource(json: String?): ArticleSource? {
        if (json == null) {
            return null
        }
        val type = object : TypeToken<ArticleSource>() {}.type
        return gson.fromJson(json, type)
    }
}

