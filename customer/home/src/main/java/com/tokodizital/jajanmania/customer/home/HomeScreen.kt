package com.tokodizital.jajanmania.customer.home

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
import com.tokodizital.jajanmania.core.domain.model.Category
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToProfileScreen: () -> Unit = {},
    navigateToPaymentScreen: () -> Unit = {},
    navigateToTopUpScreen: () -> Unit = {},
    navigateToEWalletScreen: () -> Unit = {},
    navigateToMySubscriptionScreen: () -> Unit = {},
    navigateToCategoryScreen: () -> Unit = {}
) {
    val subscriptionsList: List<Category> = listOf(
        Category(
            name = "Soto",
            isSubscribed = true
        ),
        Category(
            name = "Bakso",
            isSubscribed = true
        ),
        Category(
            name = "Es Lilin",
            isSubscribed = true
        ),
        Category(
            name = "Soto Medan",
            isSubscribed = true
        ),
    )
    val categoriesList: List<Category> = listOf(
        Category(
            name = "Mi Ayam",
            isSubscribed = false
        ),
        Category(
            name = "Cilok",
            isSubscribed = false
        ),
        Category(
            name = "Bala-bala",
            isSubscribed = false
        ),
        Category(
            name = "Gorengan",
            isSubscribed = false
        ),
    )

    val listOfMenu: List<EWalletMenu> = listOf(
        EWalletMenu(
            icon = R.drawable.ic_bayar,
            label = R.string.label_bayar
        ),
        EWalletMenu(
            icon = R.drawable.ic_add,
            label = R.string.label_topUp
        ),
        EWalletMenu(
            icon = R.drawable.ic_more_vertical,
            label = R.string.label_others
        ),
    )

    val onMenuClick: (EWalletMenu) -> Unit = {
        when (it.label) {
            R.string.label_bayar -> navigateToPaymentScreen()
            R.string.label_topUp -> navigateToTopUpScreen()
            R.string.label_others -> navigateToEWalletScreen()
        }
    }

    Scaffold(
        topBar = { HomeTopAppBar(onProfileClicked = navigateToProfileScreen) },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(vertical = 24.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                EWalletHomeSection(
                    modifier = Modifier.fillMaxWidth(),
                    balance = 50000L,
                    menuList = listOfMenu,
                    onMenuClick = onMenuClick
                )
            }
            item {
                CategoryCollection(
                    title = "Langganan Notifikasi Saya",
                    list = subscriptionsList,
                    onMoreClick = navigateToMySubscriptionScreen,
                )
            }
            item {
                CategoryCollection(
                    title = "Kategori",
                    list = categoriesList,
                    onMoreClick = navigateToCategoryScreen,
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
