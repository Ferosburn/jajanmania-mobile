package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun DetailTransactionScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {}
) {

    var transactionPaymentMethod by remember { mutableStateOf("Non-Tunai") }
    var transactionStatus by remember { mutableStateOf("Menunggu Pembayaran") }
    var transactionTime by remember { mutableStateOf("15:33:45") }
    var transactionDate by remember { mutableStateOf("3 Oktober 2023") }
    var transactionIdentifier by remember { mutableStateOf("776F345CE-5") }
    var transactionTotal by remember { mutableStateOf("Rp.58.000") }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                DetailTransactionHeader(
                    photo = "",
                    transactionCode = 12345665)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Rincian Transaksi", style = Typography.titleMedium)
                Spacer(modifier = Modifier.size(16.dp))
                Column{
                    DetailTransactionBody(
                        tittle = "Metode Pembayaran",
                        body = transactionPaymentMethod
                    )
                    DetailTransactionBody(
                        tittle = "Status",
                        body = transactionStatus
                    )
                    DetailTransactionBody(
                        tittle = "Waktu",
                        body = transactionTime
                    )
                    DetailTransactionBody(
                        tittle = "Tanggal",
                        body = transactionDate
                    )
                    DetailTransactionBody(
                        tittle = "ID Transaksi",
                        body = transactionIdentifier
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    DetailTransactionBody(
                        tittle = "Total",
                        body = transactionTotal
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
            ) {
                BaseButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    text = "Batalkan",
                    containerColor = Color.Red
                )
                BaseButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    text = "Bayar Tunai"
                )
            }
            BaseButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                text = "Tutup"
            )
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