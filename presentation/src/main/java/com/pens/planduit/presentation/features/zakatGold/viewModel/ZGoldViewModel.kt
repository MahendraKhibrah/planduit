package com.pens.planduit.presentation.features.zakatGold.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.GoldZakatRequest
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.domain.usecases.GetGoldZakatCalculationUsecase
import com.pens.planduit.presentation.features.zakatGold.state.ZakatGoldState
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ZGoldViewModel @Inject constructor(
    private val usecase: GetGoldPriceUsecase,
    private val calculationUsecase: GetGoldZakatCalculationUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(GoldPriceState())
    val state = _state.asStateFlow()

    private val _resultState = MutableStateFlow(ZakatGoldState())
    val resultState = _resultState.asStateFlow()

    var textFieldValue = MutableStateFlow("")

    fun getGoldPrice(){
        _state.value = GoldPriceState(isLoading = true)
        viewModelScope.launch (Dispatchers.IO){
            when(val result = usecase.execute(Unit)){
                is Resource.Success -> {
                    _state.value = GoldPriceState(data = result.data ?: GoldPrice(price = 0), isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = GoldPriceState(isError = true)
                }
                is Resource.Loading -> {
                    _state.value = GoldPriceState(isLoading = true)
                }
            }
        }
    }

    fun getGoldZakatCalculation(){
        _resultState.value = ZakatGoldState(isLoading = true, zakatRequest = Utils.removeCommasToInt(textFieldValue.value))
        viewModelScope.launch (Dispatchers.IO){
            when(val result = calculationUsecase.execute(getCalculationRequest())){
                is Resource.Success -> {
//                    _resultState.value = ZakatGoldState(data = result.data ?: ZakatResult(false, 0), isLoading = false)
                    _resultState.value = _resultState.value.copy(data = result.data ?: ZakatResult(false, 0), isLoading = false)
                }
                is Resource.Error -> {
                    _resultState.value = _resultState.value.copy(isError = true)
                }
                is Resource.Loading -> {
                    _resultState.value = _resultState.value.copy(isLoading = true)
                }
            }
        }
    }

    private fun getCalculationRequest(): GoldZakatRequest {
        return GoldZakatRequest(
            Utils.removeCommasToInt(textFieldValue.value)
        )
    }
}