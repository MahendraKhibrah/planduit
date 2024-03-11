package com.pens.planduit.presentation.navigation

sealed class AppRoute(val route: String){
    object Home: AppRoute("home")
    object Splash: AppRoute("splash")

    fun withArgs(vararg args: Any?): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}