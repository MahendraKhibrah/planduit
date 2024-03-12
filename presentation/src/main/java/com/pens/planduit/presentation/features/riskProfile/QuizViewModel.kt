package com.pens.planduit.presentation.features.riskProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.models.entity.dummyQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(dummyQuestions[0])

    private var questions: List<RiskProfileQuiz> = dummyQuestions
    private var selectedQuestion : Int = 0

    fun changeChoice( choiceIndex : Int){
        questions[selectedQuestion].selectedChoice = choiceIndex
        _state.value = questions[selectedQuestion]
        Log.d("ChangeChoice", "changeChoice: ${questions[selectedQuestion].selectedChoice} $questions")
    }

    fun changePage( page : Int){
        selectedQuestion = page - 1
        Log.d("ChangePage", "changePage: ${questions[selectedQuestion].selectedChoice}")
        _state.value = questions[page - 1 ]
    }
    val state = _state.asStateFlow()
}