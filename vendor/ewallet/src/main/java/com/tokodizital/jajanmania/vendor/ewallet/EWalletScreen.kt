package com.tokodizital.jajanmania.vendor.ewallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.ewallet.component.EWalletBalanceSection
import com.tokodizital.jajanmania.vendor.ewallet.component.EWalletMenuSection
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun EWalletScreen(
    modifier: Modifier = Modifier,
    eWalletViewModel: EWalletViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    navigateToTransferBankScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {},
    navigateToShopScreen: () -> Unit = {},
) {

    val eWalletUiState by eWalletViewModel.eWalletUiState.collectAsStateWithLifecycle()
    val vendorSession = eWalletUiState.vendorSession
    val vendor = eWalletUiState.vendor

    var balance by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(key1 = Unit) {
        eWalletViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            eWalletViewModel.getVendor(
                token = session.accessToken,
                id = session.accountId
            )
        }
    }

    LaunchedEffect(key1 = vendor) {
        if (vendor is Resource.Success) {
            balance = vendor.data.balance
        }
    }

    val menu = listOf(
        EWalletMenu(
            icon = R.drawable.ic_transfer_bank,
            label = R.string.label_transfer_bank
        ),
        EWalletMenu(
            icon = R.drawable.ic_history,
            label = R.string.label_history
        ),
        EWalletMenu(
            icon = R.drawable.ic_shop,
            label = R.string.label_manage_shop
        ),
    )

    val onMenuClicked: (EWalletMenu) -> Unit = {
        when (it.label) {
            R.string.label_transfer_bank -> navigateToTransferBankScreen()
            R.string.label_history -> navigateToTransactionHistoryScreen()
            R.string.label_manage_shop -> navigateToShopScreen()
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "E-Wallet",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 24.dp, end = 16.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                EWalletBalanceSection(
                    modifier = Modifier.fillMaxWidth(),
                    balance = balance,
                    onTransferBankClicked = navigateToTransferBankScreen
                )
            }
            item {
                EWalletMenuSection(
                    menu = menu,
                    onMenuClicked = onMenuClicked,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEWalletScreen() {
    JajanManiaTheme {
        Surface {
            EWalletScreen()
        }
    }
}
