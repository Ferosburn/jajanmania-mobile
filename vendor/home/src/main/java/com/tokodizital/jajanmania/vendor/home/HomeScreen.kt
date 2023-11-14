package com.tokodizital.jajanmania.vendor.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.core.data.di.dataModule
import com.tokodizital.jajanmania.core.domain.di.domainModule
import com.tokodizital.jajanmania.core.domain.model.HomeMenu
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.uicontroller.StatusBarColor
import com.tokodizital.jajanmania.vendor.home.component.HomeBalanceSection
import com.tokodizital.jajanmania.vendor.home.component.TransactionHistoryHeaderSection
import com.tokodizital.jajanmania.vendor.home.component.TransactionHistoryItem
import com.tokodizital.jajanmania.vendor.home.component.TransactionHistoryShimmer
import com.tokodizital.jajanmania.vendor.home.di.vendorHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import org.koin.core.module.Module

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    navigateToHistoryScreen: () -> Unit = {},
    navigateToEWalletScreen: () -> Unit = {},
    navigateToAccountScreen: () -> Unit = {},
    navigateToManageShopScreen: () -> Unit = {},
    navigationToDetailTransactionScreen: (String) -> Unit = {},
) {

    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val vendorSession = homeUiState.vendorSession
//    val refreshTokenResult = homeUiState.refreshToken
    val vendor = homeUiState.vendor
    val transactionHistory = homeUiState.transactionHistory

    var balance by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            homeViewModel.getVendor(
                token = session.accessToken,
                id = session.accountId
            )
            homeViewModel.getTransactionHistories(
                token = session.accessToken,
                vendorId = session.accountId
            )
        }
    }

    LaunchedEffect(key1 = vendor) {
        if (vendor is Resource.Success) {
            balance = vendor.data.balance
        }
    }

//    LaunchedEffect(key1 = vendorSession) {
//        if (vendorSession is Resource.Success) {
//            val session = vendorSession.data
//            homeViewModel.refreshToken(
//                accountId = session.accountId,
//                accountType = session.accountType,
//                accessToken = session.accessToken,
//                refreshToken = session.refreshToken,
//                expiredAt = session.expiredAt
//            )
//        }
//    }
    
//    LaunchedEffect(key1 = refreshTokenResult) {
//        if (refreshTokenResult is Resource.Success) {
//            val refreshToken = refreshTokenResult.data
//            updateToken()
//            homeViewModel.getTransactionHistories(
//                token = refreshToken.accessToken,
//                vendorId = refreshTokenResult.data.accountId
//                vendorId = "c29a5d1d-8773-43f7-a0cd-8d10a7c73df9"
//            )
//        }
//    }

    val menu = listOf(
        HomeMenu(
            icon = R.drawable.ic_shop,
            label = R.string.label_toko
        ),
        HomeMenu(
            icon = R.drawable.ic_history,
            label = R.string.label_history
        ),
        HomeMenu(
            icon = R.drawable.ic_more_horizontal,
            label = R.string.label_others
        ),
    )

    val onMenuClicked: (HomeMenu) -> Unit = {
        when (it.label) {
            R.string.label_toko -> navigateToManageShopScreen()
            R.string.label_history -> navigateToHistoryScreen()
            R.string.label_others -> navigateToEWalletScreen()
        }
    }

    StatusBarColor(
        color = MaterialTheme.colorScheme.primaryContainer
    )

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onProfileClicked = navigateToAccountScreen
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(top = 24.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ) {

            item {
                HomeBalanceSection(
                    menu = menu,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    balance = balance,
                    onMenuClicked = onMenuClicked
                )
            }

            if (vendor is Resource.Success) {
                if (vendor.data.status.contains("OFF", false)) {
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                    item {
                        EmptyContentState(
                            title = stringResource(id = R.string.label_not_activate),
                            description = stringResource(id = R.string.desc_not_activate),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { navigateToManageShopScreen() }
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                TransactionHistoryHeaderSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onSeeOtherClicked = navigateToHistoryScreen
                )
            }

            if (transactionHistory is Resource.Error) {
                item {
                    Text(text = transactionHistory.message)
                }
            }

            if (transactionHistory is Resource.Loading) {
                items(5) {
                    TransactionHistoryShimmer(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (transactionHistory is Resource.Success) {
                items(items = transactionHistory.data, key = { it.id }) {
                    TransactionHistoryItem(
                        transactionHistory = it,
                        onClick = navigationToDetailTransactionScreen,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    JajanManiaTheme {
        val context = LocalContext.current
        Surface {
            KoinApplication(application = {
                val coreModules = listOf(dataModule, domainModule)
                val vendorModules = listOf(vendorHomeModule)
                val customerModules = listOf<Module>()
                val allModules = coreModules + vendorModules + customerModules
                androidContext(context.applicationContext)
                modules(allModules)
            }) {
                HomeScreen()
            }
        }
    }
}