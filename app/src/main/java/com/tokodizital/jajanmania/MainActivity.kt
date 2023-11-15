package com.tokodizital.jajanmania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tokodizital.jajanmania.core.data.di.dataModule
import com.tokodizital.jajanmania.core.domain.di.domainModule
import com.tokodizital.jajanmania.customer.auth.di.customerAuthModule
import com.tokodizital.jajanmania.customer.home.di.customerHomeModule
import com.tokodizital.jajanmania.customer.subscription.di.customerSubscriptionModule
import com.tokodizital.jajanmania.customer.vendor.di.customerVendorModule
import com.tokodizital.jajanmania.navigation.vendor.NavHostVendor
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.auth.di.vendorAuthModule
import com.tokodizital.jajanmania.vendor.ewallet.di.vendorEWalletModule
import com.tokodizital.jajanmania.vendor.home.di.vendorHomeModule
import com.tokodizital.jajanmania.vendor.shop.di.vendorShopModule
import com.tokodizital.jajanmania.vendor.transaction.di.vendorTransactionModule
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

@ExperimentalMaterialApi
@FlowPreview
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(application = {
                val coreModules = listOf(dataModule, domainModule)
                val vendorModules = listOf(
                    vendorAuthModule,
                    vendorHomeModule,
                    vendorShopModule,
                    vendorEWalletModule,
                    vendorTransactionModule
                )
                val customerModules = listOf(
                    customerAuthModule,
                    customerHomeModule,
                    customerVendorModule,
                    customerSubscriptionModule
                )
                val allModules = coreModules + vendorModules + customerModules
                androidContext(applicationContext)
                modules(allModules)
            }) {
                JajanManiaTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHostVendor()
                    }
                }
            }
        }
    }
}
