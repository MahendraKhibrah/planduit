package com.pens.planduit.presentation.features.zakatAgriculture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.RicePrice
import com.pens.planduit.domain.models.request.AgricultureZakatRequest
import com.pens.planduit.domain.usecases.GetRicePriceUsecase
import com.pens.planduit.presentation.features.zakatAgriculture.state.RicePriceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZAgricultureViewModel @Inject constructor(
    private val usecase: GetRicePriceUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(RicePriceState())
    val state = _state.asStateFlow()

    private val _fieldValueState = MutableStateFlow(listOf("", "-1", ""))
    val fieldValueState = _fieldValueState.asStateFlow()

    fun changeFieldValue(index: Int, state: String) {
        val temp = fieldValueState.value.toMutableList()
        temp[index] = state
        _fieldValueState.value = temp
    }

    fun getRicePrice() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = usecase.execute(Unit)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        data = result.data ?: RicePrice(price = 0),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(isError = true)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }

    fun isShowField(index: Int): Boolean {
        return when (index) {
            1 -> {
                _fieldValueState.value[0] != ""
            }

            2 -> {
                _fieldValueState.value[0] != "" && _fieldValueState.value[1] != "-1"
            }

            3 -> {
                _fieldValueState.value[0] != "" && _fieldValueState.value[1] != "-1"
            }

            else -> {
                false
            }
        }
    }

    fun getRequestString() : String{
        val request = AgricultureZakatRequest(
            totalHarvest = Utils.removeCommasToInt(_fieldValueState.value[0]),
            isWatered = _fieldValueState.value[1] != "0",
            grainPrice = if(_fieldValueState.value[2] != "") Utils.removeCommasToInt(_fieldValueState.value[2]) else _state.value.data.price,
        )

        return Gson().toJson(request)
    }
}