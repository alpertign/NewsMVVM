package com.alpertign.newsmvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alpertign.newsmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table ORDER BY id ASC")
    fun getAllArticlesFromDatabase() : Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticlesToDatabase(articles : List<Article>)

}