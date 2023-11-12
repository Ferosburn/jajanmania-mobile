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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Category
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToProfileScreen: () -> Unit = {},
    navigateToTopUpScreen: () -> Unit = {},
    navigateToEWalletScreen: () -> Unit = {},
    navigateToMySubscriptionScreen: () -> Unit = {},
    navigateToCategoryScreen: () -> Unit = {},
    navigateToNearbyVendorScreen: () -> Unit = {},
    navigateToVendorDetailScreen: (String) -> Unit = {}
) {
    val nearbyVendorList: List<NearbyVendorResult> = remember {
        listOf(
            NearbyVendorResult(
                id = "413b6758-f4b0-4692-aad6-b9d258fb1706",
                name = "Batagor Bang Tigor",
                description = "Batagor renyah di luar, lembut di dalam, mantap bumbunya"
            ),
            NearbyVendorResult(
                id = "4a868a64-7be8-4863-bdd3-550fda5d686d",
                name = "Klepon Pak Jadi",
                description = "Jual Klepon, Onde-Onde, Cenil, dan Lumpia"
            ),
            NearbyVendorResult(
                id = "c29a5d1d-8773-43f7-a0cd-8d10a7c73df9",
                name = "Soto Padang Meriah",
                description = ""
            )
        )
    }
    val subscriptionsList: List<Category> = remember {
        listOf(
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
    }
    val categoriesList: List<Category> = remember {
        listOf(
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
    }

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
            R.string.label_bayar -> navigateToNearbyVendorScreen()
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
            if (nearbyVendorList.isNotEmpty()) {
                item {
                    HomeNearbyVendorSection(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        list = nearbyVendorList.take(5),
                        onMoreClick = navigateToNearbyVendorScreen,
                        navigateToVendorDetailScreen = navigateToVendorDetailScreen
                    )
                }
            }
            item {
                if (subscriptionsList.isNotEmpty()) {
                    CategoryCollection(
                        title = stringResource(R.string.title_my_notification_subscription),
                        list = subscriptionsList.take(5),
                        onMoreClick = navigateToMySubscriptionScreen,
                    )
                } else {
                    EmptyContentState(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        title = stringResource(R.string.my_notification_subscription_empty_title),
                        description = stringResource(R.string.my_notification_subscription_empty_description)
                    )
                }
            }
            if (categoriesList.isNotEmpty()) {
                item {
                    CategoryCollection(
                        title = stringResource(R.string.title_category),
                        list = categoriesList,
                        onMoreClick = navigateToCategoryScreen,
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
        Surface {
            HomeScreen()
        }
    }
}
