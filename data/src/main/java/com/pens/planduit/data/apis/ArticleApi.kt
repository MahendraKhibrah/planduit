package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.Article
import com.pens.planduit.domain.models.entity.ArticleDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApi {
    @GET("blog/all")
    suspend fun getArticles(
        @Query("limit") limit: String,
        @Query("search") search: String
    ): Response<CommonDto<List<Article>>>

    @GET("blog/detail/{slug}")
    suspend fun getArticleBySlug(
        @Path(value="slug") slug: String
    ): Response<CommonDto<ArticleDetail>>
}