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
import com.tokodizital.jajanmania.core.domain.entity.EWalletMenu
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EWalletScreen(
    modifier: Modifier = Modifier
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

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "E-Wallet")
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
                    balance = 500000L
                )
            }
            item {
                EWalletMenuSection(
                    menu = menu,
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
