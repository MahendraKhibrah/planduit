package com.pens.planduit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pens.planduit.presentation.features.main.view.HomePage
import com.pens.planduit.presentation.features.main.view.SplashPage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AppRoute.Splash.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppRoute.Home.route) { HomePage() }
        composable(AppRoute.Splash.route) { SplashPage(navController = navController) }
    }
}