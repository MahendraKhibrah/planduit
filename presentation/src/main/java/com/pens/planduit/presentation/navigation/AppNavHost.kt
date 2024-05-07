package com.pens.planduit.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.pens.planduit.common.utils.Utils
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.presentation.features.article.view.ArticleDetailPage
import com.pens.planduit.presentation.features.article.view.ArticlePage
import com.pens.planduit.presentation.features.budgeting.view.BudgetingPage
import com.pens.planduit.presentation.features.dictionary.view.DictionaryDetailPage
import com.pens.planduit.presentation.features.investation.view.InvestationPage
import com.pens.planduit.presentation.features.investation.view.InvestationResultPage
import com.pens.planduit.presentation.features.main.view.HomePage
import com.pens.planduit.presentation.features.main.view.MainPage
import com.pens.planduit.presentation.features.main.view.SplashPage
import com.pens.planduit.presentation.features.riskProfile.view.RiskProfilePage
import com.pens.planduit.presentation.features.riskProfile.view.RiskProfileResultPage
import com.pens.planduit.presentation.features.zakatAgriculture.view.ZakatAgriculturePage
import com.pens.planduit.presentation.features.zakatAgriculture.view.ZakatAgricultureResultPage
import com.pens.planduit.presentation.features.zakatGold.view.ZakatGoldPage
import com.pens.planduit.presentation.features.zakatIncome.view.ZakatIncomePage
import com.pens.planduit.presentation.features.zakatIncome.view.ZakatIncomeResultPage
import com.pens.planduit.presentation.features.zakatSavings.view.ZSavingsResultPage
import com.pens.planduit.presentation.features.zakatSavings.view.ZakatSavingsPage
import com.pens.planduit.presentation.features.zakatTrade.view.ZakatTradePage
import com.pens.planduit.presentation.features.zakatTrade.view.ZakatTradeResultPage

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
        composable(AppRoute.Main.route) { MainPage(navController = navController) }
        composable(AppRoute.Article.route) { ArticlePage(navController = navController) }
        composable(
            route = AppRoute.ArticleDetail.route + "/{slug}",
            arguments = listOf(
                navArgument("slug") {
                    type = NavType.StringType
                }
            )
        ) {
            ArticleDetailPage(
                navController = navController,
                slug = it.arguments?.getString("slug") ?: "")
        }
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
        composable(
            route = AppRoute.ZakatIncomeResult.route + "/{request}/{goldPrice}",
            arguments = listOf(
                navArgument("request"){
                    type = NavType.StringType
                },
                navArgument("goldPrice"){
                    type = NavType.StringType
                }
            )
        ){
            val request = Gson().fromJson(it.arguments?.getString("request") ?: "", IncomeZakatRequest::class.java)
            val goldPrice = Utils.addCommasEveryThreeChars(it.arguments?.getString("goldPrice") ?: "0")
            ZakatIncomeResultPage(navController = navController, request = request, goldPrice = goldPrice)
        }
        composable(AppRoute.ZakatGold.route){ ZakatGoldPage(navController = navController)}
        composable(AppRoute.ZakatAgriculture.route){ ZakatAgriculturePage(navController = navController) }
        composable(
            route = AppRoute.ZakatAgricultureResult.route + "/{request}/{ricePrice}",
            arguments = listOf(
                navArgument("request"){
                    type = NavType.StringType
                },
                navArgument("ricePrice"){
                    type = NavType.IntType
                }
            )
        ){
            ZakatAgricultureResultPage(navController = navController, request = it.arguments?.getString("request") ?: "", ricePrice = it.arguments?.getInt("ricePrice") ?: 0)
        }
        composable(AppRoute.ZakatSaving.route){ ZakatSavingsPage(navController = navController) }
        composable(
            route = AppRoute.ZakatSavingResult.route + "/{request}/{goldPrice}",
            arguments = listOf(
                navArgument("request"){
                    type = NavType.StringType
                },
                navArgument("goldPrice"){
                    type = NavType.IntType
                }
            )
        ){
            ZSavingsResultPage(navController = navController, request = it.arguments?.getString("request") ?: "", goldPrice = it.arguments?.getInt("goldPrice") ?: 0)
        }
        composable(AppRoute.ZakatTrade.route){ ZakatTradePage(navController)}
        composable(
            route = AppRoute.ZakatTradeResult.route + "/{request}/{goldPrice}",
            arguments = listOf(
                navArgument("request"){
                    type = NavType.StringType
                },
                navArgument("goldPrice"){
                    type = NavType.IntType
                }
            )
        ){
            ZakatTradeResultPage(navController = navController, request = it.arguments?.getString("request") ?: "", goldPrice = it.arguments?.getInt("goldPrice") ?: 0)
        }
        composable(
            route = AppRoute.DictionaryDetail.route+ "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){ DictionaryDetailPage(
            navController = navController,
            id = it.arguments?.getInt("id") ?: 0
        )
        }
    }
}