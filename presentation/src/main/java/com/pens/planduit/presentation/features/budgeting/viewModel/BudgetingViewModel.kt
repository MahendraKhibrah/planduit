package com.pens.planduit.presentation.features.budgeting.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.usecases.GetBudgetCalculationUsecase
import com.pens.planduit.presentation.features.budgeting.BudgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetingViewModel @Inject constructor(
    private val useCase: GetBudgetCalculationUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(BudgetState())
    val state = _state

    var textFieldValue = MutableStateFlow("")

    fun getBudgetCalculation(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when(val result = useCase.execute(BudgetingRequest(getIncomeInteger()))){
                is Resource.Success -> {
                    Log.d("BudgetingViewModel", "getBudgetCalculation: ${result.data}")
                    _state.update { it.copy(data = result.data, isLoading = false) }
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

    private fun getIncomeInteger() : Int {
        return Utils.removeCommas(textFieldValue.value).toInt()
    }
}