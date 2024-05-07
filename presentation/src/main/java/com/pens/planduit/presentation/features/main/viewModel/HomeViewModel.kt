package com.pens.planduit.presentation.features.main.viewModel

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
class HomeViewModel @Inject constructor(
    private val useCase: GetRatingStatusUsecase,
    private val saveRatingUsecase : SaveRatingStatusUsecase,
    private val postRatingUsecase: PostRatingUsecase,
    private val getArticlesUsecase: GetArticlesUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(RatingState())
    val state = _state.asStateFlow()

    private val _articleState = MutableStateFlow(HomeArticleState())
    val articleState = _articleState.asStateFlow()

    suspend fun getRatingStatus() : Boolean{
        var result = false
        viewModelScope.launch(Dispatchers.IO) {
            result = useCase.execute(Unit)
        }.join()

        return result
    }

    fun markAsRead() {
        viewModelScope.launch(Dispatchers.IO) {
            saveRatingUsecase.execute(RatingStatus(isRatingFilled = true))
        }
    }

    fun postRating(request : RatingRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when (val result = postRatingUsecase.execute(request)) {
                is Resource.Success -> {
                    _state.update { it.copy(data = true, isLoading = false) }
                }

                is Resource.Error -> {
                    _state.update { it.copy(isError = true) }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun getArticles(){
        viewModelScope.launch(Dispatchers.IO) {
            _articleState.update { it.copy(isLoading = true) }
            when (val result = getArticlesUsecase.execute(ArticlesRequest(limit = "3"))) {
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