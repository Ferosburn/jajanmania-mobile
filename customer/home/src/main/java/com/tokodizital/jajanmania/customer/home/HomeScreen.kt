package com.tokodizital.jajanmania.customer.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Category
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    navigateToLoginScreen: () -> Unit = {},
    navigateToProfileScreen: () -> Unit = {},
    navigateToTopUpScreen: () -> Unit = {},
    navigateToEWalletScreen: () -> Unit = {},
    navigateToMySubscriptionScreen: () -> Unit = {},
    navigateToCategoryScreen: () -> Unit = {},
    navigateToNearbyVendorScreen: () -> Unit = {},
    navigateToVendorDetailScreen: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val homeUiState by homeViewModel.customerHomeUiState.collectAsState()
    val customerSession = homeUiState.customerSession
    val refreshTokenResult = homeUiState.refreshTokenResult
    val account = homeUiState.account
    val nearbyVendorResult = homeUiState.nearbyVendorResult
    val mySubscriptionResult = homeUiState.mySubscriptionResult
    val categoriesResult = homeUiState.categoriesResult
    val mySubscriptionList = homeUiState.mySubscriptionList
    val categoriesList = homeUiState.categoriesList
    val latitude = homeUiState.latitude
    val longitude = homeUiState.longitude
    val isRefresh = homeUiState.isRefresh
    var token by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    val refreshState = rememberPullRefreshState(
        refreshing = isRefresh,
        onRefresh = {
            homeViewModel.refresh(
                token = token,
                userId = userId,
                latitude = latitude,
                longitude = longitude
            )
        })

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            token = session.accessToken
            userId = session.accountId
            homeViewModel.getCustomerAccount(token = token, userId = userId)
            homeViewModel.getNearbyVendors(
                latitude = latitude,
                longitude = longitude,
                token = token
            )
            homeViewModel.getMySubscriptions(token = token, userId = userId)
            homeViewModel.getCategories(token = token, userId = userId)
        }
    }

    LaunchedEffect(key1 = account) {
        if (account is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            homeViewModel.refreshToken(
                accountId = session.accountId,
                accountType = session.accountType,
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                expiredAt = session.expiredAt,
                firebaseToken = session.firebaseToken,
            )
        }
    }

    LaunchedEffect(key1 = refreshTokenResult) {
        if (refreshTokenResult is Resource.Success && (account is Resource.Error || nearbyVendorResult is Resource.Error || mySubscriptionResult is Resource.Error || categoriesResult is Resource.Error)) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            homeViewModel.updateCustomerSession(session)
            homeViewModel.getCustomerAccount(
                token = session.accessToken,
                userId = session.accountId
            )
            homeViewModel.getNearbyVendors(
                latitude = latitude,
                longitude = longitude,
                token = session.accessToken
            )
            homeViewModel.getMySubscriptions(
                token = session.accessToken,
                userId = session.accountId
            )
            homeViewModel.getCategories(token = session.accessToken, userId = session.accountId)
        }
        if (refreshTokenResult is Resource.Error) {
            homeViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
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

    val subscribe: (Category) -> Unit = {
        homeViewModel.subscribe(
            token = token,
            userId = userId,
            category = it
        )
    }

    val unsubscribe: (Category) -> Unit = {
        homeViewModel.unsubscribe(
            token = token,
            userId = userId,
            category = it
        )
    }

    Scaffold(
        topBar = { HomeTopAppBar(onProfileClicked = navigateToProfileScreen) },
        modifier = modifier.pullRefresh(refreshState)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    EWalletHomeSection(
                        modifier = Modifier.fillMaxWidth(),
                        balance = if (account is Resource.Success) account.data.balance else 0L,
                        menuList = listOfMenu,
                        onMenuClick = onMenuClick
                    )
                }
                if (nearbyVendorResult is Resource.Success) {
                    item {
                        HomeNearbyVendorSection(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            list = nearbyVendorResult.data.filter { it.status == "ON" && it.name.isNotEmpty()}.take(5),
                            onMoreClick = navigateToNearbyVendorScreen,
                            navigateToVendorDetailScreen = navigateToVendorDetailScreen
                        )
                    }
                }
                item {
                    if (mySubscriptionList.isNotEmpty()) {
                        CategoryCollection(
                            title = stringResource(R.string.title_my_notification_subscription),
                            list = mySubscriptionList.take(5),
                            onMoreClick = navigateToMySubscriptionScreen,
                            onSubscribeClick = subscribe,
                            onUnsubscribeClick = unsubscribe
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
                            list = categoriesList.take(5),
                            onMoreClick = navigateToCategoryScreen,
                            onSubscribeClick = subscribe,
                            onUnsubscribeClick = unsubscribe
                        )
                    }
                }
            }
            PullRefreshIndicator(isRefresh, refreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}

@ExperimentalMaterialApi
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
