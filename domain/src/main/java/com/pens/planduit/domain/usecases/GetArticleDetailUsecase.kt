package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ArticleDetail
import com.pens.planduit.domain.repositories.ArticleRepository
import javax.inject.Inject

class GetArticleDetailUsecase @Inject constructor(
    private val articleRepository: ArticleRepository
) : BaseUseCase<Resource<ArticleDetail>, String>{
    override suspend fun execute(request: String): Resource<ArticleDetail> =
        articleRepository.getArticleBySlug(request)
}