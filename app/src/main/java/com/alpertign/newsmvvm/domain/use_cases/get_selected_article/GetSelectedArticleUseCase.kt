package com.alpertign.newsmvvm.domain.use_cases.get_selected_article

import com.alpertign.newsmvvm.data.repository.Repository
import com.alpertign.newsmvvm.domain.model.Article

/**
 * Created by Alperen Acikgoz on 08,August,2023
 */
class GetSelectedArticleUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(articleId : Int) : Article{
        return repository.getSelectedArticle(articleId)
    }
}