package com.tokodizital.jajanmania.customer.transaction.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.parseIso8601
import com.tokodizital.jajanmania.common.utils.toLocalDate
import com.tokodizital.jajanmania.common.utils.toLocalTime
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.TransactionHistory
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanTransactionItem
import com.tokodizital.jajanmania.ui.components.customer.CustomerTotalTransactionFooter
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun CustomerTransactionDetailScreen(
    modifier: Modifier = Modifier,
    transaction: TransactionHistory
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

    var transactionVendorName by remember { mutableStateOf("Batagor Bang Tigor") }
    var transactionPaymentMethod by remember { mutableStateOf("Non-Tunai") }
    var transactionStatus by remember { mutableStateOf(transaction.status) }
    var transactionTime by remember { mutableStateOf(transaction.createdAt.parseIso8601()!!.toLocalTime()) }
    var transactionDate by remember { mutableStateOf(transaction.createdAt.parseIso8601()!!.toLocalDate()) }
    var transactionIdentifier by remember { mutableStateOf(transaction.transactionId) }
    var transactionTotal by remember { mutableStateOf(total )}

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = {

                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    ) {
                        // TODO: Use when integrated with backend
//                    AsyncImage(
//                        model = transaction.image,
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .fillMaxSize(),
//                        placeholder = painterResource(id = R.drawable.ic_image)
//                    )
                        // TODO: Delete when integrated with backend
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primaryContainer),
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = transactionVendorName,
                        style = Typography.titleLarge
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.size(24.dp))
                        Text(text = "Rincian Transaksi", style = Typography.titleMedium)
                        Spacer(modifier = Modifier.size(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Metode Pembayaran", style = Typography.bodyMedium)
                            Text(text = transactionPaymentMethod, style = Typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Status", style = Typography.bodyMedium)
                            Text(text = transactionStatus, style = Typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Waktu", style = Typography.bodyMedium)
                            Text(text = transactionTime, style = Typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Tanggal", style = Typography.bodyMedium)
                            Text(text = transactionDate, style = Typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "ID Transaksi", style = Typography.bodyMedium)
                            Text(text = transactionIdentifier, style = Typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.size(24.dp))
                        Text(text = "Rincian", style = Typography.titleMedium)
                    }
                }

                items(items = transactionItems, key = {it.id}) {
                    CustomerJajanTransactionItem(jajan = it, count = dummyCount)
                }

                item {
                    CustomerTotalTransactionFooter(totalPrice = transactionTotal )

                    Spacer(modifier = Modifier.size(16.dp))
                    BaseOutlinedButton(
                        text = "Tutup",
                        modifier = Modifier.fillMaxWidth(),
                        onClicked = {

                        }
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCustomerTransactionDetailScreen() {
    val transaction = TransactionHistory(
        transactionId = "ID-097238921",
        vendorId = 1,
        jajanId = 1,
        price = 100000,
        image = "",
        status = "Selesai",
        createdAt = "2023-10-06T13:22:16.698Z"
    )
    JajanManiaTheme {
        Surface {
            CustomerTransactionDetailScreen(transaction = transaction)
        }
    }
}