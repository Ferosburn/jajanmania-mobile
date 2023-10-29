package com.tokodizital.jajanmania.vendor.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.HomeMenu
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.uicontroller.StatusBarColor

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToShopScreen: () -> Unit = {},
    navigateToHistoryScreen: () -> Unit = {},
    navigateToEWalletScreen: () -> Unit = {},
    navigateToEditProfileScreen: () -> Unit = {},
) {

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
            R.string.label_toko -> navigateToShopScreen()
            R.string.label_history -> navigateToHistoryScreen()
            R.string.label_others -> navigateToEWalletScreen()
        }
    }

    val history = (1..7).map {
        TransactionHistory(
            transactionId = "ID-09723892$it",
            vendorId = 1,
            jajanId = 1,
            price = 100000,
            image = "",
            status = "Pending",
            createdAt = "2023-10-06T13:22:16.698Z"
        )
    }

    StatusBarColor(
        color = MaterialTheme.colorScheme.primaryContainer
    )

    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 24.dp, end = 16.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HomeBalanceSection(
                    menu = menu,
                    modifier = Modifier.fillMaxWidth(),
                    balance = 345000L,
                    onMenuClicked = onMenuClicked
                )
            }

            item{
                Box(modifier = Modifier.clickable { navigateToEditProfileScreen() }){
                    EmptyContentState(
                        title = stringResource(id = R.string.label_not_activate),
                        description = stringResource(id = R.string.desc_not_activate)
                    )
                }
            }

            item {
                HomeContentSection(
                    history = history
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    JajanManiaTheme {
        Surface {
            HomeScreen()
        }
    }
}