package com.pens.planduit.presentation.features.riskProfile.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.models.request.RiskProfileRequest
import com.pens.planduit.domain.usecases.GetQuestionProfileRiskUsecase
import com.pens.planduit.domain.usecases.SaveRiskProfileRequestUsecase
import com.pens.planduit.presentation.features.riskProfile.state.QuestionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val usecase: GetQuestionProfileRiskUsecase,
    private val saveRequestUsecase : SaveRiskProfileRequestUsecase
) : ViewModel() {
    private val _state = MutableStateFlow(QuestionState())
    private val _isCompleteFilled = MutableStateFlow(false)
    val isCompleteFilled = _isCompleteFilled.asStateFlow()
    val state = _state.asStateFlow()

    private var selectedQuestion: Int = 0
    fun changeChoice(choiceIndex: Int) {
        // TODO : NEED TO CONVERT FROM INDEX TO REAL VALUE
        val questions = _state.value.data?.toMutableList()
        questions?.get(selectedQuestion)?.selectedChoice = choiceIndex
        _state.update { it.copy(data = questions) }
        setCompleteFilled()
        Log.d(
            "ChangeChoice",
            "changeChoice: ${questions?.get(selectedQuestion)?.selectedChoice} $questions"
        )
    }

    fun changePage(page: Int) {
        selectedQuestion = page - 1
        _state.update {
            it.copy(
                selectedData = state.value.data?.get(selectedQuestion) ?: RiskProfileQuiz(
                    0,
                    "",
                    emptyList(),
                    0
                )
            )
        }
    }

    private fun setCompleteFilled() {
        for (question in _state.value.data!!) {
            if (question.selectedChoice == null) {
                Log.d("setCompleteFilled", "setCompleteFilled: ${question.selectedChoice}")
                _isCompleteFilled.value = false
                return
            }
        }
        _isCompleteFilled.value = true
    }

    fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when (val result = usecase.execute(Unit)) {
                is com.pens.planduit.common.utils.Resource.Success -> {
                    _state.update {
                        it.copy(
                            data = result.data,
                            isLoading = false,
                            selectedData = result.data?.first() ?: RiskProfileQuiz(
                                0,
                                "",
                                emptyList(),
                                0
                            )
                        )
                    }
                }

                is com.pens.planduit.common.utils.Resource.Error -> {
                    _state.update { it.copy(isError = true) }
                }

                is com.pens.planduit.common.utils.Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun saveRiskProfileRequest() : Boolean {
        var isSuccess = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = _state.value.data?.map {
                    it.choices[it.selectedChoice ?: 0].value
                }
                if(saveRequestUsecase.execute(RiskProfileRequest(request ?: emptyList()))) {
                    isSuccess = true
                }
            } catch (e: Exception) {
                Log.d("saveRiskProfileRequest", e.message ?: "Unknown Error")
            }
        }
        return isSuccess
    }
}