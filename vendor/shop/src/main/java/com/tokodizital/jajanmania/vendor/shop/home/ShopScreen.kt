package com.tokodizital.jajanmania.vendor.shop.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseExtendedFloatingActionButton
import com.tokodizital.jajanmania.ui.components.shimmer.BaseBoxShimmer
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.components.vendor.JajanItem
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigateToAddProductScreen: () -> Unit = {},
    navigateToEditProductScreen: (String) -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    shopViewModel: ShopViewModel = koinViewModel()
) {
    val shopUiState by shopViewModel.shopUiState.collectAsState()
    val listJajanPaged = shopViewModel.listJajanPaged.collectAsLazyPagingItems()
    val vendorSession = shopUiState.vendorSession
    val refreshToken = shopUiState.refreshToken

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        shopViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            shopViewModel.updateToken(
                token = session.accessToken,
            )
            shopViewModel.updateId(
                id = session.accountId,
            )
            shopViewModel.setVendorSession(session)
        }
    }

    LaunchedEffect(key1 = listJajanPaged) {
        if (listJajanPaged.loadState.refresh is LoadState.Error) {
            val errorMessage = listJajanPaged.loadState.refresh as LoadState.Error
            Toast.makeText(context, errorMessage.error.message, Toast.LENGTH_SHORT).show()
            navigateToLoginScreen()
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Kelola Toko",
                onNavigationClicked = onNavigationClicked
            )
        },
        floatingActionButton = {
            BaseExtendedFloatingActionButton(
                icon = R.drawable.ic_add,
                text = "Tambah Produk",
                onClick = navigateToAddProductScreen
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            stickyHeader {
                Text(
                    text = "List Jajanan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }

            if (listJajanPaged.loadState.refresh is LoadState.Loading) {
                items(10) {
                    BaseBoxShimmer(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            if (listJajanPaged.loadState.refresh is LoadState.NotLoading && listJajanPaged.itemCount == 0) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                        title = "Belum Ada Produk!",
                        description = "Mohon tambahkan produk jajanan yang dijual"
                    )
                }
            }
            items(listJajanPaged.itemCount, key = { it }) {
                val jajan = listJajanPaged[it]
                if (jajan != null) {
                    JajanItem(
                        jajan = jajan,
                        onClick = { navigateToEditProductScreen(jajan.id) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}