package com.alpertign.newsmvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alpertign.newsmvvm.domain.model.Article

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table ORDER BY id ASC")
    fun getAllArticles() : List<Article>

    @Query("SELECT * FROM news_table WHERE id=:articleId")
    fun getSelectedArticle(articleId : Int) : Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles : List<Article>)

    @Query("DELETE FROM news_table")
    suspend fun deleteAllArticles()
}