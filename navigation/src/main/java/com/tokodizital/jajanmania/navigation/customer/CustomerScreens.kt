package com.tokodizital.jajanmania.navigation.customer

sealed class CustomerScreens(val route: String) {
    companion object {
        const val TRANSACTION_ID = "transactionId"
        const val VENDOR_ID = "vendorId"
    }
    object Login : CustomerScreens("login")
    object Register : CustomerScreens("register")
    object Home : CustomerScreens("home")
    object EWallet : CustomerScreens("e-wallet")
    object EWalletSetting : CustomerScreens("e-wallet-setting")
    object TopUp : CustomerScreens("top-up")
    object ChangePin : CustomerScreens("change-pin")
    object NearbyVendor : CustomerScreens("nearby-vendor")
    object NearbyVendorDetail : CustomerScreens("nearby-vendor-detail/{$VENDOR_ID}") {
        fun buildRoute(vendorId: String): String {
            return "nearby-vendor-detail/$vendorId"
        }
    }
    object Checkout : CustomerScreens("checkout")
    object CheckoutAddItem : CustomerScreens("checkout-add-item")
    object Payment : CustomerScreens("payment")
    object PaymentDetail : CustomerScreens("payment-detail")
    object TransactionHistory : CustomerScreens("transaction-history")
    object TransactionDetail : CustomerScreens("transaction-detail/{$TRANSACTION_ID}") {
        fun buildRoute(transactionId: String): String {
            return "transaction-detail/$transactionId"
        }
    }
    object Profile : CustomerScreens("profile")
    object EditProfile : CustomerScreens("edit-profile")
    object MySubscription : CustomerScreens("my-subscription")
    object Categories : CustomerScreens("categories")
}
