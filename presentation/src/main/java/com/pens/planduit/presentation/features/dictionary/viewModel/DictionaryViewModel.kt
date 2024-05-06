package com.pens.planduit.presentation.features.dictionary.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.request.DictionaryRequest
import com.pens.planduit.domain.usecases.GetDictionaryUsecase
import com.pens.planduit.presentation.features.dictionary.state.DictionaryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(
    private val useCase: GetDictionaryUsecase
) : ViewModel(){
    private val _state = MutableStateFlow(DictionaryState())
    val state = _state

    fun getDictionary(
        group : String,
        search : String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            val result = useCase.execute(DictionaryRequest(
                group = group,
                search = search
            ))
            when(result){
                is Resource.Success -> {
                    _state.update { it.copy(data = result.data ?: emptyList(), isLoading = false) }
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