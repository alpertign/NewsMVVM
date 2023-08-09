package com.alpertign.newsmvvm.presentation.news_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        getCurrentArticles()
    }

    fun getArticles(fromDate: String, toDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getArticlesByDateUseCase(fromDate,toDate).collect(){newArticles ->
                _articles.postValue(newArticles)
            }
        }
    }

    private fun getCurrentArticles() {
        getArticles(fromDate = getCurrentDate(), toDate = getTenDaysAgoDate())
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    private fun getTenDaysAgoDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -10)
        val tenDaysAgoDate = calendar.time
        return dateFormat.format(tenDaysAgoDate)
    }
}