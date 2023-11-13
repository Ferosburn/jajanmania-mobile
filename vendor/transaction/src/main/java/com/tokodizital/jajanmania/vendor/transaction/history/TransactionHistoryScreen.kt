package com.tokodizital.jajanmania.vendor.transaction.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.transaction.component.TransactionHistoryItem
import com.tokodizital.jajanmania.vendor.transaction.component.TransactionHistoryShimmer
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun TransactionHistoryScreen(
    modifier: Modifier = Modifier,
    transactionHistoryViewModel: TransactionHistoryViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    navigationToDetailTransactionScreen: () -> Unit = {},
) {

    val transactionHistoryUiState by transactionHistoryViewModel.transactionHistory.collectAsStateWithLifecycle()
    val transactionHistory = transactionHistoryViewModel.transactionHistoryPaged.collectAsLazyPagingItems()
    val vendorSession = transactionHistoryUiState.vendorSession

    LaunchedEffect(key1 = Unit) {
        transactionHistoryViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            transactionHistoryViewModel.setVendorSession(session)
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Transaksi",
                onNavigationClicked = onNavigationClicked,
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Open filter",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.End
        ) {
            stickyHeader {
                Text(
                    text = "Riwayat Transaksi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }
            if (transactionHistory.loadState.refresh is LoadState.Loading) {
                items(10) {
                    TransactionHistoryShimmer(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            if (transactionHistory.loadState.refresh is LoadState.Error) {
                val errorMessage = transactionHistory.loadState.refresh as LoadState.Error
                item {
                    Text(
                        text = errorMessage.error.message.toString(),
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp)
                    )
                }
            }
            if (transactionHistory.loadState.refresh is LoadState.NotLoading && transactionHistory.itemCount == 0) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                        title = "Belum Ada Transaksi!",
                        description = "Mohon lakukan transaksi!"
                    )
                }
            }
            items(transactionHistory.itemCount, key = { it }) {
                val history = transactionHistory[it]
                if (history != null) {
                    TransactionHistoryItem(
                        transactionHistory = history,
                        onClick = { navigationToDetailTransactionScreen() },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEmptyTransactionHistoryScreen() {
    JajanManiaTheme {
        Surface {
            TransactionHistoryScreen()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewFilledTransactionHistoryScreen() {
    JajanManiaTheme {
        Surface {
            TransactionHistoryScreen()
        }
    }
}
