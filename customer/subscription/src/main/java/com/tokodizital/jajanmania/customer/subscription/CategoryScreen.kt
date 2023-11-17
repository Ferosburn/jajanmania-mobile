package com.tokodizital.jajanmania.customer.subscription

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.cards.CategoryItemCard
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    subscriptionViewModel: SubscriptionViewModel = koinViewModel(),
    onNavigationClick: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
) {
    val context = LocalContext.current
    val subscriptionUiState by subscriptionViewModel.subscriptionUiState.collectAsState()
    val pageCount = subscriptionUiState.pageCount
    val customerSession = subscriptionUiState.customerSession
    val refreshTokenResult = subscriptionUiState.refreshTokenResult
    val subscription = subscriptionUiState.subscription
    val categories = subscriptionUiState.categories
    var token by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    val loadMoreButtonIsLoading by subscriptionViewModel.loadMoreButtonIsLoading.collectAsState(
        initial = false
    )

    LaunchedEffect(key1 = Unit) {
        subscriptionViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            token = session.accessToken
            userId = session.accountId
            subscriptionViewModel.getCategories(
                token = token, userId = userId
            )
        }
    }

    LaunchedEffect(key1 = pageCount) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            subscriptionViewModel.getCategories(
                userId = session.accountId,
                token = session.accessToken,
                pageNumber = pageCount
            )
        }
    }

    LaunchedEffect(key1 = subscription) {
        if (subscription is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            subscriptionViewModel.refreshToken(
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
        if (refreshTokenResult is Resource.Success
            && subscription is Resource.Error
        ) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            subscriptionViewModel.getCategories(
                token = session.accessToken, userId = session.accountId
            )
            subscriptionViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            subscriptionViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    fun loadMore() {
        subscriptionViewModel.addPageCount()
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Kategori", onNavigationClicked = onNavigationClick)
        }, modifier = modifier
    ) { paddingValues ->
        val columnCount = 2
        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(columnCount)
        ) {
            if (categories.isNotEmpty()) {
                items(categories) { subscription ->
                    CategoryItemCard(
                        name = subscription.name,
                        isSubscribed = subscription.isSubscribed,
                        onSubscribeClick = {
                            subscriptionViewModel.subscribe(
                                token = token,
                                userId = userId,
                                category = subscription
                            )
                        },
                        onUnsubscribeClick = {
                            subscriptionViewModel.unsubscribe(
                                token = token,
                                userId = userId,
                                category = subscription
                            )
                        }
                    )
                }
                item(span = { GridItemSpan(columnCount) }) {
                    BaseButton(
                        text = "Muat Lebih Banyak",
                        onClicked = { loadMore() },
                        isLoading = loadMoreButtonIsLoading
                    )
                }
            } else {
                item(span = { GridItemSpan(columnCount) }) {
                    EmptyContentState(
                        title = "Surprise!",
                        description = "Maaf kak, halaman ini kosong! : ("
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCategoryScreen() {
    JajanManiaTheme {
        Surface {
            CategoryScreen()
        }
    }
}