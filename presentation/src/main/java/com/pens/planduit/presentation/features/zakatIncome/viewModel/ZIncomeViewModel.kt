package com.pens.planduit.presentation.features.zakatIncome.viewModel

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.presentation.features.zakatIncome.state.GoldPriceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZIncomeViewModel @Inject constructor(
    private val usecase: GetGoldPriceUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(GoldPriceState())
    val state = _state.asStateFlow()

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

    fun getRequestJsonString(question1 : Int, question2: String, question3: String, question4: String) : String {
        val request = IncomeZakatRequest(
            timeType = if(question1 == 1) "YEARLY" else "MONTHLY",
            income = Utils.removeCommasToInt(question2),
            anotherIncome = Utils.removeCommasToInt(question3),
            expenditure = Utils.removeCommasToInt(question4)
        )
        return Gson().toJson(request)
    }
}