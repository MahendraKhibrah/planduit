package com.pens.planduit.presentation.features.main.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.usecases.GetRatingStatusUsecase
import com.pens.planduit.domain.usecases.SaveRatingStatusUsecase
import com.pens.planduit.domain.usecases.TestingUsecase
import com.pens.planduit.presentation.features.main.state.RatingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val useCase: GetRatingStatusUsecase,
    private val saveRatingUsecase : SaveRatingStatusUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(RatingState())
    val state = _state.asStateFlow()

    suspend fun getRatingStatus() : Boolean{
        var result = false
        viewModelScope.launch(Dispatchers.IO) {
            result = useCase.execute(Unit)
        }.join()

            Log.d("RatingViewModel", "getRatingStatus: $result")
        return result
    }

    fun markAsRead() {
        viewModelScope.launch(Dispatchers.IO) {
            saveRatingUsecase.execute(RatingStatus(isRatingFilled = true))
        }
    }
}