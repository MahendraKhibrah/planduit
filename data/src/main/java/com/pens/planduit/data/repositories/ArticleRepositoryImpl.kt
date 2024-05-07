package com.pens.planduit.data.repositories

import android.util.Log
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.ArticleApi
import com.pens.planduit.domain.models.entity.Article
import com.pens.planduit.domain.models.entity.ArticleDetail
import com.pens.planduit.domain.models.request.ArticlesRequest
import com.pens.planduit.domain.repositories.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticleApi
) : ArticleRepository {
    override suspend fun getArticles(request: ArticlesRequest): Resource<List<Article>> {

        try {
            val response = api.getArticles(
                search = request.search,
                limit = request.limit,
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        it.data ?: emptyList()
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getArticles: ${e.message}")
            return Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getArticleBySlug(request: String): Resource<ArticleDetail> {
        try {
            val response = api.getArticleBySlug(
                slug = request
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        it.data ?: ArticleDetail(
                            id = 0,
                            title = "",
                            slug = "",
                            thumbnail = "",
                            createdAt = "",
                            updatedAt = "",
                            categoryName = "",
                            description = "",
                            status = "",
                        )
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getArticleBySlug: ${e.message}")
            return Resource.Error(e.message ?: "An error occurred")
        }
    }

}