package com.pens.planduit.domain.models.entity

data class RatingStatus (
    val isZakatGoldFilled : Boolean = false,
    val isZakatIncomeFilled : Boolean = false,
    val isZakatTrading : Boolean = false,
    val isZakatSaving : Boolean = false,
    val isZakatAgricultureFilled : Boolean = false,
    val isBudgetingFilled : Boolean = false,
    val isInvestmentFilled : Boolean = false,
    val isRiskProfileFilled : Boolean = false,
    val isRatingFilled : Boolean = false
)

fun RatingStatus.toArray(): BooleanArray {
    return booleanArrayOf(
        this.isZakatGoldFilled,
        this.isZakatIncomeFilled,
        this.isZakatTrading,
        this.isZakatSaving,
        this.isZakatAgricultureFilled,
        this.isBudgetingFilled,
        this.isInvestmentFilled,
        this.isRiskProfileFilled,
        this.isRatingFilled
    )
}

fun BooleanArray.toRatingStatus(): RatingStatus {
    return RatingStatus(
        isZakatGoldFilled = this[0],
        isZakatIncomeFilled = this[1],
        isZakatTrading = this[2],
        isZakatSaving = this[3],
        isZakatAgricultureFilled = this[4],
        isBudgetingFilled = this[5],
        isInvestmentFilled = this[6],
        isRiskProfileFilled = this[7],
        isRatingFilled = this[8]
    )
}

