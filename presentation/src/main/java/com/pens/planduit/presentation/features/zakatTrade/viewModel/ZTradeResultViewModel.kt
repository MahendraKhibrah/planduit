package com.pens.planduit.presentation.features.zakatTrade.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.SavingZakatRequest
import com.pens.planduit.domain.models.request.TradingZakatRequest
import com.pens.planduit.domain.usecases.GetSavingZakatCalculationUsecase
import com.pens.planduit.domain.usecases.GetTradingZakatUsecase
import com.pens.planduit.presentation.features.zakatSavings.state.SavingZakatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZTradeResultViewModel @Inject constructor(
    private val usecase: GetTradingZakatUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(SavingZakatState())
    val state = _state.asStateFlow()

    fun getRequestFromString(request: String) : TradingZakatRequest {
        return Gson().fromJson(request, TradingZakatRequest::class.java)
    }

    fun getTradingZakat(requestRaw : String){
        val request : TradingZakatRequest = getRequestFromString(requestRaw)

        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = usecase.execute(request)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        data = result.data ?: ZakatResult(status = false, zakat = 0),
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
}