package com.tokodizital.jajanmania.navigation.customer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.core.domain.model.TransactionItem
import com.tokodizital.jajanmania.customer.auth.LoginScreenCust
import com.tokodizital.jajanmania.customer.auth.RegisterScreenCust
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
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

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
                navigateToPaymentScreen = navController::navigateToPaymentScreen,
                navigateToEWalletScreen = navController::navigateToEWalletScreen,
                navigateToMySubscriptionScreen = navController::navigateToMySubscriptionScreen,
                navigateToCategoryScreen = navController::navigateToCategoryScreen,
                navigateToTopUpScreen = {}
            )
        }
        composable(CustomerScreens.MySubscription.route) {
            MySubscriptionScreen(
                onNavigationClick = navController::navigateUp
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
                navigateToEWalletSettingScreen = navController::navigateToEWalletSettingScreen,
                navigateToTopUpScreen = {},
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
    }
}

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