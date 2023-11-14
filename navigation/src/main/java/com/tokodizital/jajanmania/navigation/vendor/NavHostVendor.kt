package com.tokodizital.jajanmania.navigation.vendor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.account.edit.EditAccountScreen
import com.tokodizital.jajanmania.vendor.account.profil.AccountScreen
import com.tokodizital.jajanmania.vendor.auth.login.LoginScreen
import com.tokodizital.jajanmania.vendor.auth.register.RegisterScreen
import com.tokodizital.jajanmania.vendor.cashier.addtransaction.AddTransactionScreen
import com.tokodizital.jajanmania.vendor.cashier.home.CashierScreen
import com.tokodizital.jajanmania.vendor.ewallet.EWalletScreen
import com.tokodizital.jajanmania.vendor.home.HomeScreen
import com.tokodizital.jajanmania.vendor.shop.add.FormAddProductScreen
import com.tokodizital.jajanmania.vendor.shop.edit.FormEditProductScreen
import com.tokodizital.jajanmania.vendor.shop.home.ShopScreen
import com.tokodizital.jajanmania.vendor.shop.manage.ManageShopScreen
import com.tokodizital.jajanmania.vendor.transaction.detail.DetailTransactionScreen
import com.tokodizital.jajanmania.vendor.transaction.history.TransactionHistoryScreen
import org.koin.compose.koinInject

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NavHostVendor(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    vendorSessionUseCase: VendorSessionUseCase = koinInject()
) {

    val vendorSession by vendorSessionUseCase.vendorSession.collectAsState(Resource.Empty)

    if (vendorSession is Resource.Success) {

//        val startDestination = if ((vendorSession as Resource.Success<VendorSession>).data.accessToken.isNotEmpty()) {
//            VendorScreens.Dashboard.route
//        } else {
//            VendorScreens.Login.route
//        }
        val startDestination = VendorScreens.Login.route

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {

            composable(VendorScreens.Register.route) {
                RegisterScreen(
                    navigateToLoginScreen = navController::navigateToLoginScreen
                )
            }
            composable(VendorScreens.Login.route) {
                LoginScreen(
                    navigateToRegisterScreen = navController::navigateToRegisterScreen,
                    navigateToDashboardScreen = navController::navigateToDashboardScreen
                )
            }
            composable(VendorScreens.Dashboard.route) {
                HomeScreen(
                    navigateToEWalletScreen = navController::navigateToEWalletScreen,
                    navigateToManageShopScreen = navController::navigateToManageShopScreen,
                    navigateToHistoryScreen = navController::navigateToTransactionHistoryScreen,
                    navigateToAccountScreen = navController::navigateToAccountScreen,
                    navigationToDetailTransactionScreen = navController::navigateToDetailTransactionScreen
                )
            }
            composable(VendorScreens.EWallet.route) {
                EWalletScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigateToTransferBankScreen = {},
                    navigateToTransactionHistoryScreen = navController::navigateToTransactionHistoryScreen,
                    navigateToManageShopScreen = navController::navigateToManageShopScreen
                )
            }
            composable(VendorScreens.Shop.route) {
                val listJajanan: List<Jajan> = remember {
                    (1..10).map {
                        Jajan(
                            id = it,
                            vendorId = it,
                            name = "Soto",
                            category = "Makanan Kuah",
                            price = 120000L,
                            image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                        )
                    }
                }
                ShopScreen(
                    listJajanan = listJajanan,
                    onNavigationClicked = navController::navigateUp,
                    navigateToAddProductScreen = navController::navigateToAddProductScreen,
                    navigateToEditProductScreen = navController::navigateToEditProductScreen
                )
            }
            composable(VendorScreens.AddProduct.route) {
                FormAddProductScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigationToShopScreen = navController::navigateToShopScreen
                )
            }
            composable(VendorScreens.EditProduct.route) {
                FormEditProductScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigationToShopScreen = navController::navigateToShopScreen
                )
            }
            composable(VendorScreens.AddTransaction.route) {
                val listJajanan: List<Jajan> by remember {
                    mutableStateOf(listOf(
                        Jajan(
                            id = 1,
                            vendorId = 1,
                            name = "Soto",
                            category = "Makanan Kuah",
                            price = 120000L,
                            image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                        ),
                        Jajan(
                            id = 2,
                            vendorId = 1,
                            name = "Batagor Isi 7",
                            category = "Tahu Isi",
                            price = 10000L,
                            image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                        )
                    ))
                }
                AddTransactionScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigationToCashierScreen = navController::navigateToCashierScreen,
                    listJajanan = listJajanan
                )
            }
            composable(VendorScreens.TransactionHistory.route) {
                TransactionHistoryScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigationToDetailTransactionScreen = navController::navigateToDetailTransactionScreen
                )
            }
            composable(VendorScreens.ManageShop.route) {
                ManageShopScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigateManageProduct = navController::navigateToShopScreen
                )
            }
            composable(VendorScreens.Account.route) {
                AccountScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigateToEditAccount = navController::navigateToEditAccountScreen
                )
            }
            composable(VendorScreens.EditAccount.route) {
                EditAccountScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigateToProfileScreen = navController::navigateToAccountScreen
                )
            }
            composable(
                route = VendorScreens.DetailTransaction.route,
                arguments = listOf(
                    navArgument(VendorScreens.TRANSACTION_ID) {
                        type = NavType.StringType
                    }
                )
            ) {backStackEntry ->
                val transactionId = backStackEntry.arguments?.getString(VendorScreens.TRANSACTION_ID) ?: ""
                DetailTransactionScreen(
                    onNavigationClicked = navController::navigateUp,
                    transactionId = transactionId
                )
            }

            // UNUSED SCREEN
            composable(VendorScreens.Cashier.route) {
                var listJajanan: List<Jajan> by remember {
                    mutableStateOf(listOf(
                        Jajan(
                            id = 1,
                            vendorId = 1,
                            name = "Soto",
                            category = "Makanan Kuah",
                            price = 120000L,
                            image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                        ),
                        Jajan(
                            id = 2,
                            vendorId = 1,
                            name = "Batagor Isi 7",
                            category = "Tahu Isi",
                            price = 10000L,
                            image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                        )
                    ))
                }
                CashierScreen(
                    onNavigationClicked = navController::navigateUp,
                    navigationToAddTransactionScreen = navController::navigateToAddTransactionScreen,
                    navigationToDetailTransactionScreen = {},
                    listJajanan = listJajanan,
                    onDecreaseClicked = {
                        val jajanan = listJajanan.toMutableList()
                        jajanan.remove(it)
                        listJajanan = jajanan
                    },
                    onIncreaseClicked = {
                        val jajanan = listJajanan.toMutableList()
                        jajanan.add(it)
                        listJajanan = jajanan
                    },
                )
            }


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
