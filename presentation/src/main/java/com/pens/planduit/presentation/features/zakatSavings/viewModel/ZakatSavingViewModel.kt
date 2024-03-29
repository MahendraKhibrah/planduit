package com.pens.planduit.presentation.features.zakatSavings.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.request.SavingZakatRequest
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZakatSavingViewModel @Inject constructor(
    private val usecase: GetGoldPriceUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(GoldPriceState())
    val state = _state.asStateFlow()

    private val _fieldValueState = MutableStateFlow(listOf("", "-1", "",))
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
        val request = SavingZakatRequest(
            savings = Utils.removeCommasToInt(fieldValueState.value[0]),
            bank = Utils.removeCommasToInt(fieldValueState.value[1]) == 1,
            interest = if (fieldValueState.value[2] != "") Utils.removeCommasToInt(fieldValueState.value[2]) else 0
        )
        return Gson().toJson(request)
    }

    fun changeFieldValue(index: Int, state: String) {
        val temp = fieldValueState.value.toMutableList()
        temp[index] = state
        _fieldValueState.value = temp
    }

    fun isShowField(index: Int): Boolean {
        return when (index) {
            1 -> {
                _fieldValueState.value[0] != ""
            }

            2 -> {
                _fieldValueState.value[0] != "" && _fieldValueState.value[1].toInt()  > 0
            }

            3 -> {
                if (_fieldValueState.value[1].toInt() == 1) {
                    _fieldValueState.value[0] != "" && _fieldValueState.value[1] != "-1" && _fieldValueState.value[2] != ""
                } else {
                    _fieldValueState.value[0] != "" && _fieldValueState.value[1]  != "-1"
                }
            }

            else -> {
                false
            }
        }
    }

}