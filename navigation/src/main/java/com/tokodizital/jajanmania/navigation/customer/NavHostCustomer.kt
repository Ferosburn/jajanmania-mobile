package com.tokodizital.jajanmania.navigation.customer

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.customer.auth.login.LoginScreenCust
import com.tokodizital.jajanmania.customer.auth.register.RegisterScreenCust
import com.tokodizital.jajanmania.customer.cart.additem.CheckoutAddItemScreen
import com.tokodizital.jajanmania.customer.cart.home.CheckoutScreen
import com.tokodizital.jajanmania.customer.ewallet.EWalletScreenCust
import com.tokodizital.jajanmania.customer.home.HomeScreen
import com.tokodizital.jajanmania.customer.payment.PaymentDetailScreen
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
        composable(CustomerScreens.PaymentDetail.route) {
            val vendorName by remember { mutableStateOf("Batagor Bang Tigor") }
            val balance by remember { mutableLongStateOf(20000L) }
            val listItems: List<JajanItem> by remember {
                mutableStateOf(
                    listOf(
                        JajanItem(
                            id = "1",
                            name = "Soto",
                            category = "Kuah",
                            price = 10000L,
                            imageUrl = "",
                            quantity = 1
                        ),
                        JajanItem(
                            id = "2",
                            name = "Batagor",
                            category = "Kering",
                            price = 14000L,
                            imageUrl = "",
                            quantity = 2
                        ),
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
                navigateToLoginScreen = { navController.navigateToCustomerLoginScreen(route = CustomerScreens.Home.route)}
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
                navArgument(CustomerScreens.VENDOR_ID) {type = NavType.StringType}
            )
        ) {
            CustomerVendorDetailScreen(
                navigateUp = navController::navigateUp,
                navigateToCheckoutScreen = { navController.navigateToCheckoutScreen(false) },
                navigateToLoginScreen = navController::navigateToCustomerLoginScreen
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
                navigationToProcessTransactionScreen = navController::navigateToPaymentDetailScreen,
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