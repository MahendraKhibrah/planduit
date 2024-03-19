package com.pens.planduit.presentation.features.investation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.usecases.GetInvestmentCalculationUsecase
import com.pens.planduit.domain.usecases.GetInvestmentRequestUsecase
import com.pens.planduit.presentation.features.investation.state.InvestmentResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InvestmentResultViewModel @Inject constructor(
    private val getInvestmentRequestUsecase: GetInvestmentRequestUsecase,
    private val getInvestmentResultUsecase: GetInvestmentCalculationUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(InvestmentResultState())
    val state = _state

    fun getInvestmentResult() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            val result = getInvestmentRequestUsecase.execute(Unit)
            _state.update { it.copy(investmentRequest = result, isLoading = true) }
            when (val investmentResult = getInvestmentResultUsecase.execute(result!!)) {
                is Resource.Success -> {
                    _state.update { it.copy(investmentResult = investmentResult.data, isLoading = false) }
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


}