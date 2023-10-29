package com.tokodizital.jajanmania.navigation.customer

sealed class CustomerScreens(val route: String) {
    object Login : CustomerScreens("login")
    object Register : CustomerScreens("register")
    object Home : CustomerScreens("home")
    object EWallet : CustomerScreens("e-wallet")
    object EWalletSetting : CustomerScreens("e-wallet-setting")
    object ChangePin : CustomerScreens("change-pin")
    object VendorSelectionScreen : CustomerScreens("vendor-select")
    object VendorDetailScreen : CustomerScreens("vendor-detail")
    object Payment : CustomerScreens("payment")
    object PaymentDetail : CustomerScreens("payment-detail")
    object TransactionHistory : CustomerScreens("transaction-history")
    object TransactionDetail : CustomerScreens("transaction-detail")
    object Profile : CustomerScreens("profile")
    object EditProfile : CustomerScreens("edit-profile")
    object MySubscription : CustomerScreens("my-subscription")
    object Categories : CustomerScreens("categories")
}
