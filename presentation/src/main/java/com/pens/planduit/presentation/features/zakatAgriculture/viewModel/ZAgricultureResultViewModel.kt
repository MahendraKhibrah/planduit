package com.pens.planduit.presentation.features.zakatAgriculture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.AgricultureZakatRequest
import com.pens.planduit.domain.usecases.GetAgricultureZakatUsecase
import com.pens.planduit.presentation.features.zakatAgriculture.state.AgricultureZakatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ZAgricultureResultViewModel @Inject constructor(
    private val usecase: GetAgricultureZakatUsecase,
) : ViewModel() {
    private val _state = MutableStateFlow(AgricultureZakatState())
    val state = _state.asStateFlow()

    fun getRequestModel(request: String) : AgricultureZakatRequest {
        return Gson().fromJson(request, AgricultureZakatRequest::class.java)
    }

    fun getTotalHarvest(request: String) : String {
        val req = getRequestModel(request)
        return Utils.addCommasEveryThreeChars(req.totalHarvest)
    }

    fun getWateredValue(request: String) : String {
        val req = getRequestModel(request)
        return if(req.isWatered) "Menggunakan hujan / mata air" else "Menggunakan irigasi"
    }

    fun getAgricultureZakat(requestRaw : String){
        val request : AgricultureZakatRequest = getRequestModel(requestRaw)

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