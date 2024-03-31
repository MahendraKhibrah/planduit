package com.pens.planduit.presentation.features.zakatTrade.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.request.SavingZakatRequest
import com.pens.planduit.domain.models.request.TradingZakatRequest
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZakatTradeViewModel @Inject constructor(
    private val usecase: GetGoldPriceUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(GoldPriceState())
    val state = _state.asStateFlow()

    private val _fieldValueState = MutableStateFlow(listOf("", "", "", "", ""))
    val fieldValueState = _fieldValueState.asStateFlow()

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

    fun getRequestString() : String {
        val request = TradingZakatRequest(
            rotatedCapital = Utils.removeCommasToInt(fieldValueState.value[0]),
            currentRecievable = Utils.removeCommasToInt(fieldValueState.value[1]),
            profit = Utils.removeCommasToInt(fieldValueState.value[2]),
            currentPayable = Utils.removeCommasToInt(fieldValueState.value[3]),
            loss = Utils.removeCommasToInt(fieldValueState.value[4])
        )
        return Gson().toJson(request)
    }

    fun changeFieldValue(index: Int, state: String) {
        val temp = fieldValueState.value.toMutableList()
        temp[index] = state
        _fieldValueState.value = temp
    }

    fun isShowField(index: Int): Boolean {
        var isShow = true
        for (i in 0..<index) {
            if (_fieldValueState.value[i] == "") {
                isShow = false
                break
            }
        }
        return isShow
    }

}