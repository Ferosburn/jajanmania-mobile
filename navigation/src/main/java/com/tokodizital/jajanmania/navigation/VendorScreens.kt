package com.tokodizital.jajanmania.navigation

sealed class VendorScreens(val route: String) {

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

}