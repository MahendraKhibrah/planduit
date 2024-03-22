package com.pens.planduit.presentation.navigation

sealed class AppRoute(val route: String){
    object Home: AppRoute("home")
    object Splash: AppRoute("splash")
    object Article: AppRoute("article")
    object ArticleDetail: AppRoute("article-detail")
    object RiskProfile: AppRoute("risk-profile")
    object InvestmentCalculator: AppRoute("investment-calculator")
    object InvestmentResult: AppRoute("investment-result")
    object Budgeting: AppRoute("budgeting")
    object RiskProfileResult: AppRoute("risk-profile-result")
    object ZakatIncome : AppRoute("zakat-income")
    object ZakatIncomeResult : AppRoute("zakat-income-result")

    fun withArgs(vararg args: Any?): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}