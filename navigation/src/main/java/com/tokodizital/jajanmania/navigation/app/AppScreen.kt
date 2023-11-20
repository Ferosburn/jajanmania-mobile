package com.tokodizital.jajanmania.navigation.app

sealed class AppScreen(val route: String) {
    object SplashScreen: AppScreen("splash")
    object LoginOption: AppScreen("login-options")
}