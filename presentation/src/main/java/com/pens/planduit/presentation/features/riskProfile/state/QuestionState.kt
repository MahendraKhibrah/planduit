package com.pens.planduit.presentation.features.riskProfile.state

import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.RiskProfileQuiz


data class QuestionState(
    val data : List<RiskProfileQuiz>? = emptyList(),
    val selectedData : RiskProfileQuiz = RiskProfileQuiz(0, "", emptyList(), 0),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)