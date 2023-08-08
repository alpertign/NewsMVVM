package com.alpertign.newsmvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alpertign.newsmvvm.data.local.dao.NewsDao
import com.alpertign.newsmvvm.domain.model.Article

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
@Database(entities = [Article::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}