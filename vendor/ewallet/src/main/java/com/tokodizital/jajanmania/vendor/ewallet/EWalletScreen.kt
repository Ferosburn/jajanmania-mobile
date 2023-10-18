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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EWalletScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigateToCashierScreen: () -> Unit = {},
    navigateToTransferBankScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {},
    navigateToShopScreen: () -> Unit = {},
) {

    val menu = listOf(
        EWalletMenu(
            icon = R.drawable.ic_cashier,
            label = R.string.label_cashier
        ),
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
            R.string.label_cashier -> navigateToCashierScreen()
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
                    balance = 500000L,
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
