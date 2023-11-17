package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.common.utils.parseIso8601
import com.tokodizital.jajanmania.common.utils.toLocalDate
import com.tokodizital.jajanmania.common.utils.toLocalTime
import com.tokodizital.jajanmania.core.data.di.dataModule
import com.tokodizital.jajanmania.core.domain.di.domainModule
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography
import com.tokodizital.jajanmania.vendor.transaction.component.DetailTransactionBody
import com.tokodizital.jajanmania.vendor.transaction.component.JajanTransactionItem
import com.tokodizital.jajanmania.vendor.transaction.component.TotalTransactionFooter
import com.tokodizital.jajanmania.vendor.transaction.di.vendorTransactionModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@ExperimentalMaterial3Api
@Composable
fun DetailTransactionScreen(
    modifier: Modifier = Modifier,
    detailTransactionViewModel: DetailTransactionViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    transactionId: String = ""
) {

    val detailTransactionUiState by detailTransactionViewModel.detailTransactionUiState.collectAsStateWithLifecycle()
    val vendorSession = detailTransactionUiState.vendorSession
    val transactionHistory = detailTransactionUiState.transactionHistory

    var transactionTotal by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(key1 = Unit) {
        detailTransactionViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            detailTransactionViewModel.getDetailTransaction(
                token = session.accessToken,
                transactionId = transactionId
            )
        }
    }

    LaunchedEffect(key1 = transactionHistory) {
        if (transactionHistory is Resource.Success) {
            transactionHistory.data?.transactionItems?.forEach {
                val quantity = it.quantity
                val price = it.jajanItem.price
                transactionTotal = (quantity * price).toDouble()
            }
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        if (transactionHistory is Resource.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator()
            }
        }
        if (transactionHistory is Resource.Error) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(transactionHistory.message)
            }
        }
        if (transactionHistory is Resource.Success) {
            val transaction = transactionHistory.data
            if (transaction == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Transaksi tidak temukan!")
                }
                return@Scaffold
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp)
            ){
                item {
                    Text(text = "Rincian Transaksi", style = Typography.titleMedium)
                }
                item {
                    Spacer(modifier = Modifier.size(16.dp))
                }
                item {
                    DetailTransactionBody(
                        title = "Metode Pembayaran",
                        body = transaction.paymentMethod
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                }
                item {
                    DetailTransactionBody(
                        title = "Waktu",
                        body = transaction.createdAt.parseIso8601()?.toLocalTime() ?: ""
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                }
                item {
                    DetailTransactionBody(
                        title = "Tanggal",
                        body = transaction.createdAt.parseIso8601()?.toLocalDate() ?: ""
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                }
                item {
                    DetailTransactionBody(
                        title = "ID Transaksi",
                        body = transaction.id
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(24.dp))
                }
                item {
                    Text(text = "Rincian", style = Typography.titleMedium)
                }
                items(items = transaction.transactionItems, key = { it.id }) {
                    JajanTransactionItem(jajanItem = it.jajanItem, quantity = it.quantity)
                }
                item {
                    TotalTransactionFooter(totalPrice = transactionTotal)
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
        val context = LocalContext.current
        Surface {
            KoinApplication(application = {
                val coreModules = listOf(dataModule, domainModule)
                val vendorModules = listOf(vendorTransactionModule)
                val allModules = coreModules + vendorModules
                androidContext(context.applicationContext)
                modules(allModules)
            }) {
                DetailTransactionScreen()
            }
        }
    }
}