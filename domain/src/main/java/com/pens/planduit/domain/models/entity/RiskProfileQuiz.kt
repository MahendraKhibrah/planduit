package com.pens.planduit.domain.models.entity

data class RiskProfileQuiz (
    val number : Int,
    val question : String,
    val choices : List<RiskProfileChoice>
)

data class RiskProfileChoice (
    val label : String,
    val value : Int
)