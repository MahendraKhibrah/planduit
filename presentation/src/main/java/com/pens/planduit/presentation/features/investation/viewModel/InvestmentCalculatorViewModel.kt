package com.pens.planduit.presentation.features.investation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.usecases.SaveInvestmentRequestUsecase
import com.pens.planduit.domain.usecases.SaveRatingStatusUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestmentCalculatorViewModel @Inject constructor(
    private val saveInvestmentRequestUsecase: SaveInvestmentRequestUsecase,
    private val saveRatingUsecase : SaveRatingStatusUsecase
) : ViewModel() {
    private val _fieldValueState = MutableStateFlow(listOf("", "", "", "", "", "", ""))

    val fieldValueState = _fieldValueState.asStateFlow()

    fun changeFieldFilledState(index: Int, state: String) {
        val temp = fieldValueState.value.toMutableList()
        temp[index] = state
        _fieldValueState.value = temp
    }

    fun showFieldByIndex(index: Int): Boolean {
        for (i in 0..index) {
            if (fieldValueState.value[i].isEmpty()) {
                return false
            }
        }
        return true
    }

    fun saveInvestmentRequest() {
        val request = getInvestmentRequestObject()
        viewModelScope.launch(Dispatchers.IO) {
            saveInvestmentRequestUsecase.execute(request)
            saveRatingUsecase.execute(RatingStatus(isInvestmentFilled = true))
        }
    }

    private fun getInvestmentRequestObject() : InvestmentRequest{
        return InvestmentRequest(
            targetMoney = Utils.removeCommas(fieldValueState.value[0]).toInt(),
            targetTime = Utils.removeCommas(fieldValueState.value[1]).toInt(),
            initialMoney = Utils.removeCommas(fieldValueState.value[2]).toInt(),
            moneyInvestment = Utils.removeCommas(fieldValueState.value[3]).toInt(),
            interest = Utils.removeCommas(fieldValueState.value[4]).toInt(),
            timeType = if (fieldValueState.value[5] == "0") "MONTHLY" else "YEARLY"
        )
    }
}