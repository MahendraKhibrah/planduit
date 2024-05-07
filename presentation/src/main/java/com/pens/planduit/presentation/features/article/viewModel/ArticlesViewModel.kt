package com.pens.planduit.presentation.features.article.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.models.request.ArticlesRequest
import com.pens.planduit.domain.models.request.RatingRequest
import com.pens.planduit.domain.usecases.GetArticlesUsecase
import com.pens.planduit.domain.usecases.GetRatingStatusUsecase
import com.pens.planduit.domain.usecases.PostRatingUsecase
import com.pens.planduit.domain.usecases.SaveRatingStatusUsecase
import com.pens.planduit.presentation.features.main.state.HomeArticleState
import com.pens.planduit.presentation.features.main.state.RatingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticlesUsecase: GetArticlesUsecase
) : ViewModel() {

    private val _articleState = MutableStateFlow(HomeArticleState())
    val articleState = _articleState.asStateFlow()

    fun getArticles(searchKey : String = ""){
        viewModelScope.launch(Dispatchers.IO) {
            _articleState.update { it.copy(isLoading = true) }
            when (val result = getArticlesUsecase.execute(ArticlesRequest(search= searchKey)) ) {
                is Resource.Success -> {
                    _articleState.update { it.copy(
                        data = result.data ?: emptyList(),
                        isLoading = false)
                    }
                }

                is Resource.Error -> {
                    _articleState.update { it.copy(isError = true) }
                }

                is Resource.Loading -> {
                    _articleState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}