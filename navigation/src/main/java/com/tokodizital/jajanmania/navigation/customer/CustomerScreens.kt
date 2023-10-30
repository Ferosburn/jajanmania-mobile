package com.tokodizital.jajanmania.navigation.customer

sealed class CustomerScreens(val route: String) {
    object Login : CustomerScreens("login")
    object Register : CustomerScreens("register")
    object Home : CustomerScreens("home")
    object EWallet : CustomerScreens("e-wallet")
    object EWalletSetting : CustomerScreens("e-wallet-setting")
    object ChangePin : CustomerScreens("change-pin")
    object NearbyVendor : CustomerScreens("nearby-vendor")
    object NearbyVendorDetail : CustomerScreens("nearby-vendor-detail")
    object Checkout : CustomerScreens("checkout")
    object CheckoutAddItem : CustomerScreens("checkout-add-item")
    object Payment : CustomerScreens("payment")
    object PaymentDetail : CustomerScreens("payment-detail")
    object TransactionHistory : CustomerScreens("transaction-history")
    object TransactionDetail : CustomerScreens("transaction-detail")
    object Profile : CustomerScreens("profile")
    object EditProfile : CustomerScreens("edit-profile")
    object MySubscription : CustomerScreens("my-subscription")
    object Categories : CustomerScreens("categories")
}
