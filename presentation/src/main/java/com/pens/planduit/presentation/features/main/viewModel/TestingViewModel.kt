package com.pens.planduit.presentation.features.main.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.usecases.TestingUsecase
import com.pens.planduit.presentation.features.main.state.TestingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestingViewModel @Inject constructor(
    private val useCase: TestingUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(TestingState())
    val state = _state.asStateFlow()

    fun getInvestmentNotAchieved(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when (val result = useCase.execute(Unit)){
                is Resource.Success -> {
                    _state.update { it.copy(data = result.data) }
                }

                is Resource.Error -> {
                    _state.update { it.copy(isError = true) }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
            _state.update { it.copy(isLoading = false) }
        }

    }
}