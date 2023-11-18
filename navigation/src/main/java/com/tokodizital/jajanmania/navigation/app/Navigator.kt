package com.tokodizital.jajanmania.navigation.app

import androidx.navigation.NavHostController

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToLoginOptionScreen(
    clearBackStack: Boolean = true,
    route: String = AppScreen.SplashScreen.route
) {
    navigate(AppScreen.LoginOption.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}