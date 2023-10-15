package com.tokodizital.jajanmania.customer.transaction.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.customer.CustomerTransactionHistoryItem
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CustomerTransactionHistoryScreen(
    modifier: Modifier = Modifier,
    history: List<TransactionHistory> = emptyList()
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Transaksi",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Open filter",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
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
            if (history.isEmpty()) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                        title = "Belum Ada Transaksi!",
                        description = "Beli jajan dulu, yuk!"
                    )
                }
            }
            items(items = history, key = { it.transactionId }) {
                CustomerTransactionHistoryItem(
                    transactionHistory = it,
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
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

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewFilledTransactionHistoryScreen() {
    JajanManiaTheme {
        Surface {
            val listJajanan: List<TransactionHistory> = remember {
                (1..10).map {
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
            }
            CustomerTransactionHistoryScreen(
                history = listJajanan
            )
        }
    }
}
