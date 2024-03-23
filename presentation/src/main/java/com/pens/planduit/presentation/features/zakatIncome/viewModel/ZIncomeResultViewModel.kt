package com.pens.planduit.presentation.features.zakatIncome.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.domain.usecases.GetIncomeZakatCalculationUsecase
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import com.pens.planduit.presentation.features.zakatIncome.state.ZIncomeResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZIncomeResultViewModel @Inject constructor(
    private val usecase: GetIncomeZakatCalculationUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(ZIncomeResultState())
    val state = _state.asStateFlow()

    fun getZakatIncome(request: IncomeZakatRequest){
        _state.value = ZIncomeResultState(isLoading = true)
        viewModelScope.launch (Dispatchers.IO){
            when(val result = usecase.execute(request)){
                is Resource.Success -> {
                    _state.value = ZIncomeResultState(data = result.data ?: ZakatResult(true, 0), isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = ZIncomeResultState(isError = true)
                }
                is Resource.Loading -> {
                    _state.value = ZIncomeResultState(isLoading = true)
                }
            }
        }
    }
}