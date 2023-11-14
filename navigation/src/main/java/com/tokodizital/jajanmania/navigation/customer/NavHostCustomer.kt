package com.tokodizital.jajanmania.navigation.customer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import com.tokodizital.customer.topup.CustomerTopUpScreen
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.core.domain.model.TransactionItem
import com.tokodizital.jajanmania.customer.auth.login.LoginScreenCust
import com.tokodizital.jajanmania.customer.auth.register.RegisterScreenCust
import com.tokodizital.jajanmania.customer.cart.additem.CheckoutAddItemScreen
import com.tokodizital.jajanmania.customer.cart.home.CheckoutScreen
import com.tokodizital.jajanmania.customer.ewallet.EWalletChangePinScreen
import com.tokodizital.jajanmania.customer.ewallet.EWalletScreenCust
import com.tokodizital.jajanmania.customer.ewallet.EWalletSettingScreen
import com.tokodizital.jajanmania.customer.home.HomeScreen
import com.tokodizital.jajanmania.customer.payment.PaymentDetailScreen
import com.tokodizital.jajanmania.customer.payment.PaymentScreen
import com.tokodizital.jajanmania.customer.profile.EditProfileScreen
import com.tokodizital.jajanmania.customer.profile.ProfileScreen
import com.tokodizital.jajanmania.customer.subscription.CategoryScreen
import com.tokodizital.jajanmania.customer.subscription.MySubscriptionScreen
import com.tokodizital.jajanmania.customer.transaction.detail.CustomerTransactionDetailScreen
import com.tokodizital.jajanmania.customer.transaction.history.CustomerTransactionHistoryScreen
import com.tokodizital.jajanmania.customer.vendor.detail.CustomerVendorDetailScreen
import com.tokodizital.jajanmania.customer.vendor.nearbyvendor.CustomerVendorScreen
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NavHostCustomer(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val startDestination = CustomerScreens.Login.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(CustomerScreens.Register.route) {
            RegisterScreenCust(navigateToLoginScreen = navController::navigateToLoginScreen)
        }
        composable(CustomerScreens.Login.route) {
            LoginScreenCust(
                navigateToHomeScreen = navController::navigateToHomeScreen,
                navigateToRegisterScreen = navController::navigateToRegisterScreen
            )
        }
        composable(CustomerScreens.Home.route) {
            HomeScreen(
                navigateToProfileScreen = navController::navigateToProfileScreen,
                navigateToEWalletScreen = navController::navigateToEWalletScreen,
                navigateToMySubscriptionScreen = navController::navigateToMySubscriptionScreen,
                navigateToCategoryScreen = navController::navigateToCategoryScreen,
                navigateToTopUpScreen = navController::navigateToTopUpScreen,
                navigateToNearbyVendorScreen = navController::navigateToNearbyVendorScreen,
                navigateToVendorDetailScreen = navController::navigateToNearbyVendorDetailScreen
            )
        }
        composable(CustomerScreens.MySubscription.route) {
            MySubscriptionScreen(
                onNavigationClick = navController::navigateUp,
                navigateToLoginScreen = navController::navigateToLoginScreen
            )
        }
        composable(CustomerScreens.Categories.route) {
            CategoryScreen(
                onNavigationClick = navController::navigateUp
            )
        }
        composable(CustomerScreens.EWallet.route) {
            EWalletScreenCust(
                onNavigationClick = navController::navigateUp,
                navigateToPaymentScreen = navController::navigateToPaymentScreen,
                navigateToTransactionHistoryScreen = navController::navigateToTransactionHistoryScreen,
//                navigateToEWalletSettingScreen = navController::navigateToEWalletSettingScreen,
                navigateToTopUpScreen = navController::navigateToTopUpScreen,
            )
        }
        composable(CustomerScreens.Payment.route) {
            PaymentScreen(
                onNavigationClick = navController::navigateUp,
                navigateToPaymentDetailScreen = navController::navigateToPaymentDetailScreen,
            )
        }
        composable(CustomerScreens.PaymentDetail.route) {
            val vendorName by remember { mutableStateOf("Batagor Bang Tigor") }
            val balance by remember { mutableLongStateOf(20000L) }
            val listItems: List<TransactionItem> by remember {
                mutableStateOf(
                    listOf(
                        TransactionItem(
                            jajan = Jajan(
                                id = 1,
                                vendorId = 1,
                                name = "Soto",
                                category = "Kuah",
                                price = 10000L,
                                image = ""
                            ),
                            amount = 1
                        ),
                        TransactionItem(
                            jajan = Jajan(
                                id = 1,
                                vendorId = 1,
                                name = "Batagor",
                                category = "Kering",
                                price = 14000L,
                                image = "",
                            ),
                            amount = 2
                        )
                    )
                )
            }
            PaymentDetailScreen(
                navigateUp = navController::navigateUp,
                navigateToHomeScreen = { navController.navigateToHomeScreen(route = CustomerScreens.Home.route) },
                listJajanan = listItems,
                vendorName = vendorName,
                balance = balance
            )
        }
        composable(CustomerScreens.TopUp.route) {
            CustomerTopUpScreen(
                navigateUp = navController::navigateUp,
                navigateToHomeScreen = { navController.navigateToHomeScreen(route = CustomerScreens.Home.route) }
            )
        }
        composable(CustomerScreens.TransactionHistory.route) {
            val listJajanan: List<TransactionHistory> = remember {
                (1..10).map {
                    TransactionHistory(
                        transactionId = "ID-09723892$it",
                        vendorId = 1,
                        jajanId = 1,
                        price = 100000,
                        image = "",
                        status = "Pending",
                        createdAt = "2023-10-06T13:22:16.698Z"
                    )
                }
            }
            CustomerTransactionHistoryScreen(
                history = listJajanan,
                navigateToTransactionDetailScreen = navController::navigateToTransactionDetailScreen,
                onNavigationClick = navController::navigateUp
            )
        }
        composable(CustomerScreens.TransactionDetail.route) {
            val transaction = remember {
                TransactionHistory(
                    transactionId = "ID-097238921",
                    vendorId = 1,
                    jajanId = 1,
                    price = 100000,
                    image = "",
                    status = "Selesai",
                    createdAt = "2023-10-06T13:22:16.698Z"
                )
            }
            CustomerTransactionDetailScreen(
                transaction = transaction,
                onNavigationClick = navController::navigateUp
            )
        }
        composable(CustomerScreens.EWalletSetting.route) {
            EWalletSettingScreen(
                onNavigationClick = navController::navigateUp,
                navigateToChangePinScreen = navController::navigateToChangePinScreen
            )
        }
        composable(CustomerScreens.ChangePin.route) {
            EWalletChangePinScreen(
                onNavigationClick = navController::navigateUp,
                navigateToEWalletSettingScreen = navController::navigateToEWalletSettingScreen
            )
        }
        composable(CustomerScreens.Profile.route) {
            ProfileScreen(
                onNavigationClick = navController::navigateUp,
                navigateToEditProfileScreen = navController::navigateToEditProfileScreen,
                navigateToTransactionHistoryScreen = navController::navigateToTransactionHistoryScreen,
                navigateToMySubscriptionScreen = navController::navigateToMySubscriptionScreen,
                navigateToLoginScreen = navController::navigateToLoginScreen
            )
        }
        composable(CustomerScreens.EditProfile.route) {
            EditProfileScreen(
                onNavigationClick = navController::navigateUp,
                navigateToProfileScreen = navController::navigateToProfileScreen
            )
        }
        composable(CustomerScreens.NearbyVendor.route) {
            CustomerVendorScreen(
                onNavigationClick = navController::navigateUp,
                navigateToVendorDetailScreen = navController::navigateToNearbyVendorDetailScreen,
                navigateToLoginScreen = navController::navigateToLoginScreen
            )
        }
        composable("${CustomerScreens.NearbyVendorDetail.route}/{vendorId}", arguments = listOf(navArgument("vendorId") {type = NavType.StringType})) {
            CustomerVendorDetailScreen(
                navigateUp = navController::navigateUp,
                navigateToCheckoutScreen = navController::navigateToCheckoutScreen,
                navigateToLoginScreen = navController::navigateToLoginScreen
            )
        }
        composable(CustomerScreens.Checkout.route) {
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
            CheckoutScreen(
                onNavigationClicked = navController::navigateUp,
                navigationToAddItemScreen = navController::navigateToCheckoutAddItemScreen,
                navigationToProcessTransactionScreen = {},
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
                }
            )
        }
        composable(CustomerScreens.CheckoutAddItem.route) {
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
            CheckoutAddItemScreen(
                onNavigationClicked = navController::navigateUp,
                navigationToCheckoutScreen = navController::navigateToCheckoutScreen,
                listJajanan = listJajanan
            )
        }
    }
}

@FlowPreview
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewNavHostCustomer() {
    JajanManiaTheme {
        Surface {
            NavHostCustomer()
        }
    }
}