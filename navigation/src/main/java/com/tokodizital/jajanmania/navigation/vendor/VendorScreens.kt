package com.tokodizital.jajanmania.navigation.vendor

sealed class VendorScreens(val route: String) {

    companion object {
        const val TRANSACTION_ID = "transactionId"
    }

    object Login : VendorScreens("login")

    object Register : VendorScreens("register")

    object Dashboard : VendorScreens("dashboard")

    object EWallet : VendorScreens("ewallet")

    object Shop : VendorScreens("shop")

    object AddProduct : VendorScreens("add-product")

    object EditProduct : VendorScreens("edit-product")

    object Cashier : VendorScreens("cashier")

    object AddTransaction : VendorScreens("add-transaction")

    object TransactionHistory : VendorScreens("transaction-history")

    object DetailTransaction : VendorScreens("detail-transaction/{$TRANSACTION_ID}") {
        fun buildRoute(transactionId: String): String {
            return "detail-transaction/$transactionId"
        }
    }

    object ManageShop : VendorScreens("manage-shop")

    object Account : VendorScreens("account")

    object EditAccount : VendorScreens("edit-account")

}
