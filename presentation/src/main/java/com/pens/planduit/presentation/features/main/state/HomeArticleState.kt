package com.pens.planduit.presentation.features.main.state

import com.pens.planduit.domain.models.entity.Article

data class HomeArticleState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: List<Article> = emptyList()
)
