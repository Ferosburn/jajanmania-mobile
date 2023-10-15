package com.tokodizital.jajanmania.navigation.vendor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.cashier.addtransaction.AddTransactionScreen
import com.tokodizital.jajanmania.vendor.cashier.home.CashierScreen
import com.tokodizital.jajanmania.vendor.ewallet.EWalletScreen
import com.tokodizital.jajanmania.vendor.home.HomeScreen
import com.tokodizital.jajanmania.vendor.shop.add.FormAddProductScreen
import com.tokodizital.jajanmania.vendor.shop.edit.FormEditProductScreen
import com.tokodizital.jajanmania.vendor.shop.home.ShopScreen
import com.tokodizital.jajanmania.vendor.transaction.history.TransactionHistoryScreen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NavHostVendor(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val startDestination = VendorScreens.EWallet.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(VendorScreens.Register.route) {}
        composable(VendorScreens.Login.route) {}
        composable(VendorScreens.Dashboard.route) {
            HomeScreen()
        }
        composable(VendorScreens.EWallet.route) {
            EWalletScreen()
        }
        composable(VendorScreens.Shop.route) {
            ShopScreen()
        }
        composable(VendorScreens.AddProduct.route) {
            FormAddProductScreen()
        }
        composable(VendorScreens.EditProduct.route) {
            FormEditProductScreen()
        }
        composable(VendorScreens.Cashier.route) {
            CashierScreen()
        }
        composable(VendorScreens.AddTransaction.route) {
            AddTransactionScreen()
        }
        composable(VendorScreens.TransactionHistory.route) {
            TransactionHistoryScreen()
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewNavHostVendor() {
    JajanManiaTheme {
        Surface {
            NavHostVendor()
        }
    }
}
