package com.alpertign.newsmvvm.domain.use_cases.get_articles_by_date

import com.alpertign.newsmvvm.data.repository.Repository
import com.alpertign.newsmvvm.domain.model.ApiResponse
import retrofit2.Response

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class GetArticlesByDateUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(fromDate : String, toDate: String) : Response<ApiResponse> {
        return repository.getArticlesByDate(fromDate, toDate)
    }
}