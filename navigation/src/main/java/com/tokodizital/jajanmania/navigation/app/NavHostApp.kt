package com.tokodizital.jajanmania.navigation.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tokodizital.jajanmania.customer.auth.splashscreen.LoginOptionScreen
import com.tokodizital.jajanmania.customer.auth.splashscreen.SplashScreen
import com.tokodizital.jajanmania.navigation.customer.CustomerScreens
import com.tokodizital.jajanmania.navigation.customer.NavHostCustomer
import com.tokodizital.jajanmania.navigation.customer.navigateToCustomerLoginScreen
import com.tokodizital.jajanmania.navigation.customer.navigateToHomeScreen
import com.tokodizital.jajanmania.navigation.vendor.NavHostVendor
import com.tokodizital.jajanmania.navigation.vendor.VendorScreens
import com.tokodizital.jajanmania.navigation.vendor.navigateToDashboardScreen
import com.tokodizital.jajanmania.navigation.vendor.navigateToLoginScreen

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NavHostApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val startDestination = AppScreen.SplashScreen.route

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(
                navigateToLoginOptionScreen = navController::navigateToLoginOptionScreen,
                navigateToCustomerHomeScreen = navController::navigateToHomeScreen,
                navigateToVendorHomeScreen = { navController.navigateToDashboardScreen(route = AppScreen.SplashScreen.route) }
            )
        }
        composable(AppScreen.LoginOption.route) {
            LoginOptionScreen(
                navigateToCustomerLoginScreen = navController::navigateToCustomerLoginScreen,
                navigateToVendorLoginScreen = navController::navigateToLoginScreen
            )
        }
        composable(CustomerScreens.Login.route) {
            NavHostCustomer()
        }
        composable(CustomerScreens.Home.route) {
            NavHostCustomer(startDestination = CustomerScreens.Home.route)
        }
        composable(VendorScreens.Login.route) {
            NavHostVendor()
        }
        composable(VendorScreens.Dashboard.route) {
            NavHostVendor(startDestination = VendorScreens.Dashboard.route)
        }
    }
}