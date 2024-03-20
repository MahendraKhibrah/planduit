package com.pens.planduit.presentation.features.riskProfile.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.usecases.GetQuestionProfileRiskUsecase
import com.pens.planduit.domain.usecases.GetRiskProfileResultUsecase
import com.pens.planduit.presentation.features.riskProfile.state.QuestionState
import com.pens.planduit.presentation.features.riskProfile.state.RiskProfileResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiskProfileResultViewModel @Inject constructor(
    private val usecase: GetRiskProfileResultUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(RiskProfileResultState())
    val state = _state.asStateFlow()

    fun getRiskProfileResult() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when (val result = usecase.execute(Unit)) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isError = false, data = result.data) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = true) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }


}