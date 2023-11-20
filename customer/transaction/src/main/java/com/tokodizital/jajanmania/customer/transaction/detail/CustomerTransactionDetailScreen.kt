package com.tokodizital.jajanmania.customer.transaction.detail

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.common.data.PaymentMethod
import com.tokodizital.jajanmania.common.utils.parseIso8601
import com.tokodizital.jajanmania.common.utils.toLocalDate
import com.tokodizital.jajanmania.common.utils.toLocalTime
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanTransactionItem
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun CustomerTransactionDetailScreen(
    modifier: Modifier = Modifier,
    customerTransactionDetailViewModel: CustomerTransactionDetailViewModel = koinViewModel(),
    onNavigationClick: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
) {
    val context = LocalContext.current
    val customerTransactionDetailUiState by customerTransactionDetailViewModel.customerTransactionDetailUiState.collectAsState()
    val customerSession = customerTransactionDetailUiState.customerSession
    val transactionDetailResult = customerTransactionDetailUiState.transactionDetailResult
    val refreshTokenResult = customerTransactionDetailUiState.refreshTokenResult

    LaunchedEffect(key1 = Unit) {
        customerTransactionDetailViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            val transactionId = customerTransactionDetailViewModel.transactionId
            customerTransactionDetailViewModel.getTransactionDetail(
                token = session.accessToken,
                transactionId = transactionId,
            )
        }
    }

    LaunchedEffect(key1 = transactionDetailResult) {
        if (transactionDetailResult is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            customerTransactionDetailViewModel.refreshToken(
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
            && transactionDetailResult is Resource.Error
        ) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            val transactionId = customerTransactionDetailViewModel.transactionId
            customerTransactionDetailViewModel.getTransactionDetail(
                token = session.accessToken,
                transactionId = transactionId,
            )
            customerTransactionDetailViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            customerTransactionDetailViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = onNavigationClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (transactionDetailResult is Resource.Success) {
                    val datetime = transactionDetailResult.data.transactionDatetime.parseIso8601()
                    item {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                        ) {
                            AsyncImage(
                                model = transactionDetailResult.data.vendorImage,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .fillMaxSize(),
                                placeholder = painterResource(id = R.drawable.ic_jajan_mania_48),
                                error = painterResource(id = R.drawable.ic_jajan_mania_48)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = transactionDetailResult.data.vendorName,
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
                                Text(
                                    text = PaymentMethod.values()
                                        .first { it.name == transactionDetailResult.data.paymentMethod }
                                        .displayName,
                                    style = Typography.bodyMedium
                                )
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Waktu", style = Typography.bodyMedium)
                                Text(text = datetime!!.toLocalTime(), style = Typography.bodyMedium)
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Tanggal", style = Typography.bodyMedium)
                                Text(text = datetime!!.toLocalDate(), style = Typography.bodyMedium)
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val transactionId = transactionDetailResult.data.transactionId
                                val index = transactionId.length / 2
                                Text(text = "ID Transaksi", style = Typography.bodyMedium)
                                Text(
                                    text = transactionDetailResult.data.transactionId.replaceRange(
                                        index,
                                        index,
                                        "\n"
                                    ),
                                    style = Typography.bodyMedium,
                                    textAlign = TextAlign.End
                                )
                            }
                            Spacer(modifier = Modifier.size(24.dp))
                            Text(text = "Rincian", style = Typography.titleMedium)
                        }
                    }
                    items(items = transactionDetailResult.data.jajanItems, key = { it.id }) {
                        CustomerJajanTransactionItem(jajan = it, count = it.quantity)
                    }
                    item {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(start = 64.dp, end = 80.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Total",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = transactionDetailResult.data.totalPrice.toInt().toRupiah(),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCustomerTransactionDetailScreen() {
    JajanManiaTheme {
        Surface {
            CustomerTransactionDetailScreen()
        }
    }
}