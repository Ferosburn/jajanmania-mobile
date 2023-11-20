package com.tokodizital.jajanmania.navigation.customer

import androidx.navigation.NavHostController
import com.tokodizital.jajanmania.navigation.app.AppScreen

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToRegisterScreen() {
    navigate(CustomerScreens.Register.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToCustomerLoginScreen(
    clearBackStack: Boolean = true,
    route: String = AppScreen.LoginOption.route
) {
    navigate(CustomerScreens.Login.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToHomeScreen(
    clearBackStack: Boolean = true,
    route: String = AppScreen.SplashScreen.route
) {
    navigate(CustomerScreens.Home.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToProfileScreen(
    clearBackStack: Boolean = true,
    route: String = CustomerScreens.Profile.route
) {
    navigate(CustomerScreens.Profile.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToEditProfileScreen() {
    navigate(CustomerScreens.EditProfile.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToTopUpScreen() {
    navigate(CustomerScreens.TopUp.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToEWalletScreen() {
    navigate(CustomerScreens.EWallet.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToMySubscriptionScreen() {
    navigate(CustomerScreens.MySubscription.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToCategoryScreen() {
    navigate(CustomerScreens.Categories.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToTransactionHistoryScreen() {
    navigate(CustomerScreens.TransactionHistory.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToTransactionDetailScreen(transactionId: String) {
    navigate(CustomerScreens.TransactionDetail.buildRoute(transactionId))
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToNearbyVendorScreen() {
    navigate(CustomerScreens.NearbyVendor.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToNearbyVendorDetailScreen(vendorId: String) {
    navigate(CustomerScreens.NearbyVendorDetail.buildRoute(vendorId))
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToCheckoutScreen(vendorId: String) {
    navigate(CustomerScreens.Cart.buildRoute(vendorId))
}

//@Throws(IllegalArgumentException::class)
//fun NavHostController.navigateToCheckoutScreenFromCheckoutAddItemScreen(
//    jajanItem: String,
//    clearBackStack: Boolean = true,
////    route: String = CustomerScreens.Checkout.route,
//) {
//    Log.i("TEST", "from add item")
//    navigate(CustomerScreens.Checkout.buildRoute(jajanItem)) {
//        if (clearBackStack) {
//            popUpTo(CustomerScreens.Checkout.buildRoute(jajanItem)) { inclusive = true }
//        }
//    }
//}