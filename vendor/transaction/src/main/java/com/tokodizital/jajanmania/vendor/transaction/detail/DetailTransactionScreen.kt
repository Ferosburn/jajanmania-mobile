package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanTransactionItem
import com.tokodizital.jajanmania.ui.components.customer.CustomerTotalTransactionFooter
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun DetailTransactionScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {}
) {

    val dummyJajan = listOf(
        Jajan(1, 1, "Batagor Isi 5", "Gorengan", 10000L, ""),
        Jajan(3, 1, "Batagor Isi 10", "Gorengan", 18000L, ""),
        Jajan(2, 1, "Batagor Isi 7", "Gorengan", 14000L, "")
    )
    val dummyCount = 2
    var transactionItems by remember {
        mutableStateOf(
            dummyJajan
        )
    }
    var total = 0
    for(item in transactionItems) {
        total += (item.price.toInt() * dummyCount)
    }

    var transactionPaymentMethod by remember { mutableStateOf("Non-Tunai") }
    var transactionTime by remember { mutableStateOf("15:33:45") }
    var transactionDate by remember { mutableStateOf("3 Oktober 2023") }
    var transactionIdentifier by remember { mutableStateOf("776F345CE-5") }
    var transactionTotal by remember { mutableStateOf(total )}

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Rincian Transaksi", style = Typography.titleMedium)
                        Spacer(modifier = Modifier.size(16.dp))
                        Column{
                            DetailTransactionBody(
                                tittle = "Metode Pembayaran",
                                body = transactionPaymentMethod
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            DetailTransactionBody(
                                tittle = "Waktu",
                                body = transactionTime
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            DetailTransactionBody(
                                tittle = "Tanggal",
                                body = transactionDate
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            DetailTransactionBody(
                                tittle = "ID Transaksi",
                                body = transactionIdentifier
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(text = "Rincian", style = Typography.titleMedium)
                        }
                    }
                }
                items(items = transactionItems, key = {it.id}) {
                    CustomerJajanTransactionItem(jajan = it, count = dummyCount)
                }
                item {
                    CustomerTotalTransactionFooter(totalPrice = transactionTotal )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailTransactionScreen() {
    JajanManiaTheme {
        Surface {
            DetailTransactionScreen()
        }
    }
}