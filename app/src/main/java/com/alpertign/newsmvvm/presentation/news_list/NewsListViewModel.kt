package com.alpertign.newsmvvm.presentation.news_list

import androidx.lifecycle.ViewModel
import com.alpertign.newsmvvm.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Alperen Acikgoz on 07,August,2023
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
}