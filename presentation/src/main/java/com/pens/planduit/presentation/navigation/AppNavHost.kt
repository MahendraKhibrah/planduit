package com.pens.planduit.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pens.planduit.presentation.features.article.view.ArticleDetailPage
import com.pens.planduit.presentation.features.article.view.ArticlePage
import com.pens.planduit.presentation.features.budgeting.view.BudgetingPage
import com.pens.planduit.presentation.features.investation.view.InvestationPage
import com.pens.planduit.presentation.features.investation.view.InvestationResultPage
import com.pens.planduit.presentation.features.main.view.HomePage
import com.pens.planduit.presentation.features.main.view.SplashPage
import com.pens.planduit.presentation.features.riskProfile.view.RiskProfilePage
import com.pens.planduit.presentation.features.riskProfile.view.RiskProfileResultPage
import com.pens.planduit.presentation.features.zakatIncome.view.ZakatIncomePage
import com.pens.planduit.presentation.features.zakatIncome.view.ZakatIncomeResultPage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AppRoute.Splash.route,
) {
    NavHost(
        navController = navController, startDestination = startDestination,
        exitTransition = {
            fadeOut(tween(0))
        },
        enterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        popExitTransition = {
            fadeOut(tween(0))
        },
    ) {
        composable(AppRoute.Home.route) { HomePage(navController = navController) }
        composable(AppRoute.Splash.route) { SplashPage(navController = navController) }
        composable(AppRoute.Article.route) { ArticlePage(navController = navController) }
        composable(AppRoute.ArticleDetail.route) { ArticleDetailPage(navController = navController) }
        composable(AppRoute.RiskProfile.route) { RiskProfilePage(navController = navController) }
        composable(AppRoute.InvestmentCalculator.route) { InvestationPage(navController = navController) }
        composable(route = AppRoute.InvestmentResult.route){
            InvestationResultPage(navController = navController)
        }
        composable(AppRoute.Budgeting.route) { BudgetingPage(navController = navController) }
        composable(AppRoute.RiskProfileResult.route){
            RiskProfileResultPage(navController = navController)
        }
        composable(AppRoute.ZakatIncome.route) { ZakatIncomePage(navController = navController) }
        composable(AppRoute.ZakatIncomeResult.route) { ZakatIncomeResultPage(navController = navController) }
    }
}