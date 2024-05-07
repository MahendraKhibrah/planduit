package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.Article
import com.pens.planduit.domain.models.request.ArticlesRequest
import com.pens.planduit.domain.repositories.ArticleRepository
import javax.inject.Inject

class GetArticlesUsecase @Inject constructor(
    private val repository: ArticleRepository
) : BaseUseCase<Resource<List<Article>>, ArticlesRequest> {
    override suspend fun execute(request: ArticlesRequest):
            Resource<List<Article>> = repository.getArticles(request)
}