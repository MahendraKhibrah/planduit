package com.pens.planduit.presentation.features.investation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pens.planduit.domain.models.entity.dummyQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InvestmentCalculatorViewModel @Inject constructor(
) : ViewModel() {
    private val _fieldValueState = MutableStateFlow(listOf("", "", "", "", "", "", ""))

    val fieldValueState = _fieldValueState.asStateFlow()

    fun changeFieldFilledState(index : Int, state : String){
        val temp = fieldValueState.value.toMutableList()
        temp[index] = state
        _fieldValueState.value = temp
    }

    fun showFieldByIndex(index: Int) : Boolean {
        for (i in 0..index){
            if (fieldValueState.value[i].isEmpty()) {
                return false
            }
        }
        return true
    }
}