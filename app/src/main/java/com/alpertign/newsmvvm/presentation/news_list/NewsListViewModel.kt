package com.alpertign.newsmvvm.presentation.news_list

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpertign.newsmvvm.domain.model.ApiResponse
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.model.NetworkResult
import com.alpertign.newsmvvm.domain.use_cases.UseCases
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
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
    private val useCases: UseCases,
    application: Application
) : AndroidViewModel(application) {

    var articleResponse: MutableLiveData<NetworkResult<List<Article>>> = MutableLiveData()

    init {
        getCurrentArticles()
    }

    fun getArticles(fromDate: String, toDate: String) = viewModelScope.launch {
        getArticlesSafeCall(
            fromDate,
            toDate
        )
    }

    private suspend fun getArticlesSafeCall(fromDate: String, toDate: String) {
        articleResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {

                val response = useCases.getArticlesByDateUseCase(
                    fromDate,
                    toDate
                )
                articleResponse.value = handleApiResponse(response)

                val articles = articleResponse.value!!.data
                if (articles != null) {
                    offlineCacheArticles(articles)
                }


            } catch (e: Exception) {
                articleResponse.value = NetworkResult.Error("Articles not found.")
            }
        } else {
            articleResponse.value = NetworkResult.Error("No Internet Connection.")
        }

    }

    private fun offlineCacheArticles(articles: List<Article>) {
        //TODO("Not yet implemented")
    }

    private fun getCurrentArticles() {
        getArticles(
            fromDate = getCurrentDate(),
            toDate = getTenDaysAgoDate()
        )
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    private fun getTenDaysAgoDate(): String {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
        val calendar = Calendar.getInstance()
        calendar.add(
            Calendar.DAY_OF_YEAR,
            -10
        )
        val tenDaysAgoDate = calendar.time
        return dateFormat.format(tenDaysAgoDate)
    }

    private fun handleApiResponse(response: Response<ApiResponse>): NetworkResult<List<Article>> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }

            response.body()!!.articles.isEmpty() -> {
                return NetworkResult.Error("Articles not found.")
            }

            response.isSuccessful -> {
                val articles = response.body()!!.articles
                return NetworkResult.Success(articles)
            }

            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}