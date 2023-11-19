package com.tokodizital.jajanmania.navigation.vendor

import androidx.navigation.NavHostController
import com.tokodizital.jajanmania.navigation.app.AppScreen

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToRegisterScreen() {
    navigate(VendorScreens.Register.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToLoginScreen(clearBackStack: Boolean = true, route: String = AppScreen.LoginOption.route) {
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
fun NavHostController.navigateToCashierScreen(clearBackStack: Boolean = true, route: String = VendorScreens.Cashier.route) {
    navigate(VendorScreens.Cashier.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
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
fun NavHostController.navigateToEditProductScreen(
    jajanId: String
) {
    navigate(VendorScreens.EditProduct.buildRoute(jajanId))
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToAddTransactionScreen() {
    navigate(VendorScreens.AddTransaction.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToTransactionHistoryScreen() {
    navigate(VendorScreens.TransactionHistory.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToManageShopScreen() {
    navigate(VendorScreens.ManageShop.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToAccountScreen(clearBackStack: Boolean = true, route: String = VendorScreens.Account.route) {
    navigate(VendorScreens.Account.route) {
        if (clearBackStack) {
            popUpTo(route) { inclusive = true }
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToEditAccountScreen() {
    navigate(VendorScreens.EditAccount.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToDetailTransactionScreen(
    transactionId: String
) {
    navigate(VendorScreens.DetailTransaction.buildRoute(transactionId))
}
