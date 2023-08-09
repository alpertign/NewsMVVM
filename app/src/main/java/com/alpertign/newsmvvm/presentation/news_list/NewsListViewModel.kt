package com.alpertign.newsmvvm.presentation.news_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpertign.newsmvvm.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun getCurrentArticles(fromDate: String, toDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getCurrentArticlesUseCase(fromDate,toDate).collect(){
                Log.e("alpertign","list.size = ${it.size}")
            }
        }
    }

}