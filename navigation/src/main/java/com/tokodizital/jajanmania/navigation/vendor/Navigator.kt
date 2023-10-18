package com.tokodizital.jajanmania.navigation.vendor

import androidx.navigation.NavHostController
import kotlin.jvm.Throws

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToRegisterScreen() {
    navigate(VendorScreens.Register.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToLoginScreen(clearBackStack: Boolean = true, route: String = VendorScreens.Login.route) {
    navigate(VendorScreens.Login.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToDashboardScreen(clearBackStack: Boolean = true, route: String = VendorScreens.Login.route) {
    navigate(VendorScreens.Dashboard.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToCashierScreen() {
    navigate(VendorScreens.Cashier.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToShopScreen(clearBackStack: Boolean = true, route: String = VendorScreens.Shop.route) {
    navigate(VendorScreens.Shop.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToEWalletScreen() {
    navigate(VendorScreens.EWallet.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToAddProductScreen() {
    navigate(VendorScreens.AddProduct.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToEditProductScreen() {
    navigate(VendorScreens.EditProduct.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToAddTransactionScreen() {
    navigate(VendorScreens.AddTransaction.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToTransactionHistoryScreen() {
    navigate(VendorScreens.TransactionHistory.route)
}
