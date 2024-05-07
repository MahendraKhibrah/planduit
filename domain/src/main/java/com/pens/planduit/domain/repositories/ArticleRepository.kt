package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.Article
import com.pens.planduit.domain.models.entity.ArticleDetail
import com.pens.planduit.domain.models.request.ArticlesRequest

interface ArticleRepository {
    suspend fun getArticles(request : ArticlesRequest): Resource<List<Article>>
    suspend fun getArticleBySlug(request : String): Resource<ArticleDetail>
}

