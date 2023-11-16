package com.tokodizital.jajanmania.customer.transaction.history

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerTransaction
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerTransactionHistoryItem
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CustomerTransactionHistoryScreen(
    modifier: Modifier = Modifier,
    customerTransactionHistoryViewModel: CustomerTransactionHistoryViewModel = koinViewModel(),
    onNavigationClick: () -> Unit = {},
    navigateToTransactionDetailScreen: (String) -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
) {

    val context = LocalContext.current
    val customerTransactionHistoryUiState by customerTransactionHistoryViewModel.customerTransactionHistoryUiState.collectAsState()
    val customerSession = customerTransactionHistoryUiState.customerSession
    val refreshTokenResult = customerTransactionHistoryUiState.refreshTokenResult
    val transactionHistory = customerTransactionHistoryUiState.transactionHistory
    val transactionHistoryList = customerTransactionHistoryUiState.transactionHistoryList
    val loadMoreButtonIsLoading by customerTransactionHistoryViewModel.loadMoreButtonIsLoading.collectAsState(
        initial = false
    )

    val onItemClick: (CustomerTransaction) -> Unit = {
        navigateToTransactionDetailScreen(it.transactionId)
    }

    LaunchedEffect(key1 = Unit) {
        customerTransactionHistoryViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            customerTransactionHistoryViewModel.getTransactionHistory(
                token = session.accessToken,
                userId = session.accountId,
            )
        }
    }

    LaunchedEffect(key1 = transactionHistory) {
        if (transactionHistory is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            customerTransactionHistoryViewModel.refreshToken(
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
            && transactionHistory is Resource.Error
        ) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            customerTransactionHistoryViewModel.getTransactionHistory(
                token = session.accessToken,
                userId = session.accountId,
            )
            customerTransactionHistoryViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            customerTransactionHistoryViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Transaksi",
                onNavigationClicked = onNavigationClick
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
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }
            if (transactionHistoryList.isEmpty()) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                        title = "Belum Ada Transaksi!",
                        description = "Beli jajan dulu, yuk!"
                    )
                }
            } else {
                items(items = transactionHistoryList) {
                    CustomerTransactionHistoryItem(
                        transactionHistory = it,
                        onClick = onItemClick,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    BaseButton(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        text = "Muat Lebih Banyak",
                        onClicked = {
                            if (customerSession is Resource.Success) {
                                customerTransactionHistoryViewModel.loadMore(
                                    customerSession.data.accessToken,
                                    customerSession.data.accountId)
                            }
                        },
                        isLoading = loadMoreButtonIsLoading
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
            CustomerTransactionHistoryScreen()
        }
    }
}
