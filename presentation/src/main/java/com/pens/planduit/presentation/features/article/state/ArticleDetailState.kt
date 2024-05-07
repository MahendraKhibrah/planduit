package com.pens.planduit.presentation.features.article.state

import com.pens.planduit.domain.models.entity.ArticleDetail

data class ArticleDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: ArticleDetail? = null
)
