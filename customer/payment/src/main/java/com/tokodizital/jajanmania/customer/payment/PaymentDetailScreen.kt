package com.tokodizital.jajanmania.customer.payment

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.bottomsheet.BaseModalBottomSheet
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanTransactionItem
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

// Not in use
@ExperimentalMaterial3Api
@Composable
fun PaymentDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateToHomeScreen: () -> Unit = {},
    listJajanan: List<JajanItem> = emptyList(),
    vendorName: String,
    balance: Long
) {
    var backDialogState by remember { mutableStateOf(false) }
    var lowBalanceDialogState by remember { mutableStateOf(false) }
    var eWalletPaymentSuccessDialogState by remember { mutableStateOf(false) }
    var processCashPaymentDialogState by remember { mutableStateOf(false) }
    var cashPaymentSuccessDialogState by remember { mutableStateOf(false) }

    val totalPrice = remember(key1 = listJajanan) {
        listJajanan.sumOf { it.price * it.quantity }
    }

    if (backDialogState) {
        BaseModalBottomSheet(
            title = "Balik Ke Halaman Sebelumnya?",
            body = "Yakin?",
            positiveButtonTitle = "Ya, Yakin",
            onPositiveButtonClicked = {
                backDialogState = false
                navigateUp()
            },
            negativeButtonTitle = "Tutup",
            onNegativeButtonClicked = { backDialogState = false },
            positiveButtonContainerColor = MaterialTheme.colorScheme.error
        )
    }

    if (lowBalanceDialogState) {
        BaseModalBottomSheet(
            title = "Yah, Saldo Tidak Cukup",
            body = "Top-up dulu yuk biar saldo kamu cukup",
            positiveButtonTitle = "Top Up",
            onPositiveButtonClicked = { lowBalanceDialogState = false },
            negativeButtonTitle = "Tutup",
            onNegativeButtonClicked = { lowBalanceDialogState = false }
        )
    }

    if (eWalletPaymentSuccessDialogState) {
        BaseModalBottomSheet(
            title = "Pembayaran Sukses!",
            body = "Yeay, terima kasih telah bertransaksi",
            positiveButtonTitle = "Lanjut",
            onPositiveButtonClicked = { eWalletPaymentSuccessDialogState = false })
    }

    if (processCashPaymentDialogState) {
        BaseModalBottomSheet(
            title = "Apakah Anda Yakin Bayar Tunai?",
            body = "Siapkan uang sesuai jumlah, ya!",
            positiveButtonTitle = "Lanjut",
            onPositiveButtonClicked = {
                processCashPaymentDialogState = false
                cashPaymentSuccessDialogState = true
            },
            negativeButtonTitle = "Tutup",
            onNegativeButtonClicked = { processCashPaymentDialogState = false }
        )
    }

    if (cashPaymentSuccessDialogState) {
        BaseModalBottomSheet(
            title = "Pembayaran Sukses!",
            body = "Yeay, terima kasih telah bertransaksi",
            positiveButtonTitle = "Lanjut",
            onPositiveButtonClicked = {
                cashPaymentSuccessDialogState = false
                navigateToHomeScreen()
            })
    }

    BackHandler {
        backDialogState = true
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = { backDialogState = true })
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp, 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_jajan_mania_48),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(120.dp, 120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(text = vendorName, style = MaterialTheme.typography.headlineSmall)
            }
            Text(text = "Rincian", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(listJajanan) {
                    CustomerJajanTransactionItem(jajan = it, count = it.quantity)
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .padding(start = 64.dp, end = 80.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Total",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = totalPrice.toRupiah(),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BaseOutlinedButton(
                    text = "Bayar Tunai",
                    modifier = Modifier.weight(1f),
                    onClicked = { processCashPaymentDialogState = true })
                BaseButton(text = "Bayar Non-Tunai", modifier = Modifier.weight(1f), onClicked = {
                    if (balance < totalPrice) {
                        lowBalanceDialogState = true
                    } else {
                        eWalletPaymentSuccessDialogState = true
                    }
                })
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PaymentDetailScreenPreview() {
    JajanManiaTheme {
        Surface {
            val vendorName by remember { mutableStateOf("Batagor Bang Tigor") }
            val balance by remember { mutableLongStateOf(20000L) }
            val listItems: List<JajanItem> by remember {
                mutableStateOf(
                    listOf(
                      JajanItem(
                            id = "1",
                            name = "Soto",
                            category = "Kuah",
                            price = 10000L,
                            imageUrl = "",
                            quantity = 1
                        ),
                        JajanItem(
                            id = "2",
                            name = "Batagor",
                            category = "Kering",
                            price = 14000L,
                            imageUrl = "",
                            quantity = 2
                        ),
                    )
                )
            }
            PaymentDetailScreen(
                listJajanan = listItems,
                vendorName = vendorName,
                balance = balance
            )
        }
    }
}