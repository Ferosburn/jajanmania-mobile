package com.tokodizital.jajanmania.vendor.home

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
import com.tokodizital.jajanmania.core.domain.model.HomeMenu
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val menu = listOf(
        HomeMenu(
            icon = R.drawable.ic_cashier,
            label = R.string.label_cashier
        ),
        HomeMenu(
            icon = R.drawable.ic_shop,
            label = R.string.label_toko
        ),
        HomeMenu(
            icon = R.drawable.ic_more_horizontal,
            label = R.string.label_others
        ),
    )

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
                )
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