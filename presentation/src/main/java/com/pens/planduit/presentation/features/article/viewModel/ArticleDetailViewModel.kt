package com.pens.planduit.presentation.features.article.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.usecases.GetArticleDetailUsecase
import com.pens.planduit.presentation.features.article.state.ArticleDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val getArticleDetailUsecase: GetArticleDetailUsecase
) : ViewModel() {

    private val _articleState = MutableStateFlow(ArticleDetailState())
    val articleState = _articleState.asStateFlow()

    fun getArticles(slug : String){
        viewModelScope.launch(Dispatchers.IO) {
            _articleState.update { it.copy(isLoading = true) }
            when (val result = getArticleDetailUsecase.execute(slug)) {
                is Resource.Success -> {
                    _articleState.update { it.copy(
                        data = result.data,
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