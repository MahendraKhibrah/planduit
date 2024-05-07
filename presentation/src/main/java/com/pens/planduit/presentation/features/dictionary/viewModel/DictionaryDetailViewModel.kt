package com.pens.planduit.presentation.features.dictionary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.request.CommonIdRequest
import com.pens.planduit.domain.usecases.GetDictionaryDetailUsecase
import com.pens.planduit.presentation.features.dictionary.state.DictionaryDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryDetailViewModel @Inject constructor(
    private val useCase: GetDictionaryDetailUsecase
) : ViewModel(){
    private val _state = MutableStateFlow(DictionaryDetailState())
    val state = _state

    fun getDictionaryDetail(
        id : Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when(val result = useCase.execute(CommonIdRequest(id))){
                is Resource.Success -> {
                    _state.update { it.copy(data = result.data, isLoading = false) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isError = true) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}