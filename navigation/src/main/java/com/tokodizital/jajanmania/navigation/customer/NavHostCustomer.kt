package com.tokodizital.jajanmania.navigation.customer

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tokodizital.customer.topup.CustomerTopUpScreen
import com.tokodizital.jajanmania.customer.auth.login.LoginScreenCust
import com.tokodizital.jajanmania.customer.auth.register.RegisterScreenCust
import com.tokodizital.jajanmania.customer.cart.home.CheckoutScreen
import com.tokodizital.jajanmania.customer.ewallet.EWalletScreenCust
import com.tokodizital.jajanmania.customer.home.HomeScreen
import com.tokodizital.jajanmania.customer.profile.ProfileScreen
import com.tokodizital.jajanmania.customer.profile.manage.ManageProfileScreen
import com.tokodizital.jajanmania.customer.subscription.CategoryScreen
import com.tokodizital.jajanmania.customer.subscription.MySubscriptionScreen
import com.tokodizital.jajanmania.customer.transaction.detail.CustomerTransactionDetailScreen
import com.tokodizital.jajanmania.customer.transaction.history.CustomerTransactionHistoryScreen
import com.tokodizital.jajanmania.customer.vendor.detail.CustomerVendorDetailScreen
import com.tokodizital.jajanmania.customer.vendor.nearbyvendor.CustomerVendorScreen
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NavHostCustomer(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CustomerScreens.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(CustomerScreens.Register.route) {
            RegisterScreenCust(navigateToLoginScreen = navController::navigateToCustomerLoginScreen)
        }
        composable(CustomerScreens.Login.route) {
            LoginScreenCust(
                navigateToHomeScreen = { navController.navigateToHomeScreen(route = CustomerScreens.Login.route) },
                navigateToRegisterScreen = navController::navigateToRegisterScreen
            )
        }
        composable(CustomerScreens.Home.route) {
            HomeScreen(
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen,
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
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(CustomerScreens.Categories.route) {
            CategoryScreen(
                onNavigationClick = navController::navigateUp,
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(CustomerScreens.EWallet.route) {
            EWalletScreenCust(
                onNavigationClick = navController::navigateUp,
                navigateToNearbyVendorScreen = navController::navigateToNearbyVendorScreen,
                navigateToTransactionHistoryScreen = navController::navigateToTransactionHistoryScreen,
                navigateToTopUpScreen = navController::navigateToTopUpScreen,
            )
        }
        composable(CustomerScreens.TopUp.route) {
            CustomerTopUpScreen(
                navigateUp = navController::navigateUp,
                navigateToHomeScreen = { navController.navigateToHomeScreen(route = CustomerScreens.Home.route) },
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(CustomerScreens.TransactionHistory.route) {
            CustomerTransactionHistoryScreen(
                navigateToTransactionDetailScreen = navController::navigateToTransactionDetailScreen,
                onNavigationClick = navController::navigateUp,
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(
            route = CustomerScreens.TransactionDetail.route,
            arguments = listOf(
                navArgument(CustomerScreens.TRANSACTION_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            CustomerTransactionDetailScreen(
                onNavigationClick = navController::navigateUp,
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(CustomerScreens.Profile.route) {
            val context = LocalContext.current
            val underConstructionToast: () -> Unit = {
                Toast.makeText(context, "Halaman masih dalam pengerjaan", Toast.LENGTH_SHORT)
                    .show()
            }
            ProfileScreen(
                onNavigationClick = navController::navigateUp,
                navigateToEditProfileScreen = navController::navigateToEditProfileScreen,
                navigateToTransactionHistoryScreen = navController::navigateToTransactionHistoryScreen,
                navigateToMySubscriptionScreen = navController::navigateToMySubscriptionScreen,
                navigateToTermAndConditionScreen = underConstructionToast,
                navigateToHelpCenterScreen = underConstructionToast,
                navigateToLevelScreen = underConstructionToast,
                navigateToLoginScreen = { navController.navigateToCustomerLoginScreen(route = CustomerScreens.Home.route) }
            )
        }
        composable(CustomerScreens.EditProfile.route) {
            ManageProfileScreen(
                onNavigationClick = navController::navigateUp,
                navigateToProfileScreen = navController::navigateToProfileScreen
            )
        }
        composable(CustomerScreens.NearbyVendor.route) {
            CustomerVendorScreen(
                onNavigationClick = navController::navigateUp,
                navigateToVendorDetailScreen = navController::navigateToNearbyVendorDetailScreen,
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(
            route = CustomerScreens.NearbyVendorDetail.route,
            arguments = listOf(
                navArgument(CustomerScreens.VENDOR_ID) { type = NavType.StringType }
            )
        ) {
            CustomerVendorDetailScreen(
                navigateUp = navController::navigateUp,
                navigateToCheckoutScreen = navController::navigateToCheckoutScreen,
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
            )
        }
        composable(
            route = CustomerScreens.Cart.route,
            arguments = listOf(
                navArgument(CustomerScreens.VENDOR_ID) { type = NavType.StringType }
            )
        ) {
            CheckoutScreen(
                onNavigationClicked = navController::navigateUp,
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen,
                navigateToHomeScreen = { navController.navigateToHomeScreen(route = CustomerScreens.Home.route) },
                navigateToTopUpScreen = navController::navigateToTopUpScreen,
            )
        }
    }
}

@ExperimentalMaterialApi
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