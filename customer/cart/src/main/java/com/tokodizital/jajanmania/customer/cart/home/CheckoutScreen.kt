package com.tokodizital.jajanmania.customer.cart.home

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.JajanItem
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.bottomsheet.BaseModalBottomSheet
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanCheckoutItem
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanTransactionItem
import com.tokodizital.jajanmania.ui.components.customer.CustomerTotalTransactionFooter
import com.tokodizital.jajanmania.ui.components.customer.CustomerVendorJajanItem
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    checkoutViewModel: CheckoutViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    navigateToHomeScreen: () -> Unit = {},
    navigateToTopUpScreen: () -> Unit = {},
) {
    val context = LocalContext.current
    val checkoutUiState by checkoutViewModel.checkoutUiState.collectAsState()
    val customerSession = checkoutUiState.customerSession
    val vendorDetailResult = checkoutUiState.vendorDetailResult
    val refreshTokenResult = checkoutUiState.refreshTokenResult
    val account = checkoutUiState.account
    val checkoutList = checkoutUiState.checkoutList
    var backDialogState by remember { mutableStateOf(false) }
    var addItemDialogState by remember { mutableStateOf(false) }
    var paymentDialogState by remember { mutableStateOf(false) }
    var totalPrice by remember { mutableLongStateOf(0L) }
    var balance by remember { mutableLongStateOf(0L) }
    var buttonProcessEnabled by remember { mutableStateOf(false) }
    var lowBalanceDialogState by remember { mutableStateOf(false) }
    var eWalletPaymentSuccessDialogState by remember { mutableStateOf(false) }
    var processCashPaymentDialogState by remember { mutableStateOf(false) }
    var cashPaymentSuccessDialogState by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = checkoutList.size) {
        totalPrice = checkoutList.sumOf { it.price }
        buttonProcessEnabled = checkoutList.isNotEmpty()
        val displayedCheckoutList = checkoutList
            .distinctBy { it.id }
            .map { item ->
                JajanItem(
                    id = item.id,
                    name = item.name,
                    price = item.price,
                    imageUrl = item.imageUrl,
                    category = item.category,
                    quantity = checkoutList.filter { item.id == it.id }.size,
                )
            }
        Log.i("TEST_X", displayedCheckoutList.toString())
    }

    LaunchedEffect(key1 = Unit) {
        checkoutViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            val vendorId = checkoutViewModel.vendorId
            checkoutViewModel.getCustomerAccount(
                token = session.accessToken,
                userId = session.accountId
            )
            checkoutViewModel.getVendorDetail(
                vendorId, session.accessToken
            )
        }
    }

    LaunchedEffect(key1 = account) {
        if (account is Resource.Success) {
            balance = account.data.balance
        }
    }

    LaunchedEffect(key1 = vendorDetailResult) {
        if (vendorDetailResult is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            checkoutViewModel.refreshToken(
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
            && vendorDetailResult is Resource.Error
            && vendorDetailResult.message.contains("authorization")
        ) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            val vendorId = checkoutViewModel.vendorId
            checkoutViewModel.getCustomerAccount(
                token = session.accessToken,
                userId = session.accountId
            )
            checkoutViewModel.getVendorDetail(
                vendorId, session.accessToken
            )
            checkoutViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            checkoutViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    if (backDialogState) {
        BaseModalBottomSheet(
            title = "Balik Ke Halaman Sebelumnya?",
            body = "Apakah anda yakin ingin membatalkan pesanan?",
            positiveButtonTitle = "Ya, Yakin",
            onPositiveButtonClicked = {
                backDialogState = false
                onNavigationClicked()
            },
            negativeButtonTitle = "Tutup",
            onNegativeButtonClicked = { backDialogState = false },
            positiveButtonContainerColor = MaterialTheme.colorScheme.error
        )
    }

    if (addItemDialogState && vendorDetailResult is Resource.Success) {
        ModalBottomSheet(
            onDismissRequest = { addItemDialogState = false },
            dragHandle = {},
            sheetState = SheetState(skipPartiallyExpanded = true),
            shape = RectangleShape,
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxHeight(),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { addItemDialogState = false }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = "Tambah Item",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            Text(
                text = "List Jajanan",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .background(MaterialTheme.colorScheme.background)
            )
            LazyColumn {
                items(items = vendorDetailResult.data.jajanItems, key = { it.id }) {
                    CustomerVendorJajanItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                checkoutViewModel.addCheckoutList(it)
                                addItemDialogState = false
                            },
                        jajan = it,
                    )
                }
            }
        }
    }

    // PaymentDialog
    if (paymentDialogState && vendorDetailResult is Resource.Success && checkoutList.isNotEmpty()) {
        ModalBottomSheet(
            onDismissRequest = { paymentDialogState = false },
            dragHandle = {},
            sheetState = SheetState(skipPartiallyExpanded = true),
            shape = RectangleShape,
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxHeight(),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { paymentDialogState = false }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = "Detail Transaksi",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(16.dp, 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        AsyncImage(
                            model = vendorDetailResult.data.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp),
                            placeholder = painterResource(id = R.drawable.ic_jajan_mania_48),
                            error = painterResource(id = R.drawable.ic_jajan_mania_48),
                        )
                    }
                    Text(
                        text = vendorDetailResult.data.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                Text(text = "Rincian", style = MaterialTheme.typography.titleMedium)
                LazyColumn {
                    items(checkoutList.distinctBy { it.id }) {
                        CustomerJajanTransactionItem(
                            jajan = it,
                            count = checkoutList.filter { item -> item.id == it.id }.size
                        )
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
                    BaseButton(
                        text = "Bayar Non-Tunai",
                        modifier = Modifier.weight(1f),
                        onClicked = {
                            if (balance < totalPrice) {
                                lowBalanceDialogState = true
                            } else {
                                eWalletPaymentSuccessDialogState = true
                            }
                        })
                }
            }
        }
        if (lowBalanceDialogState) {
            BaseModalBottomSheet(
                title = "Yah, Saldo Tidak Cukup",
                body = "Top-up dulu yuk biar saldo kamu cukup",
                positiveButtonTitle = "Top Up",
                onPositiveButtonClicked = {
                    lowBalanceDialogState = false
                    navigateToTopUpScreen()
                },
                negativeButtonTitle = "Tutup",
                onNegativeButtonClicked = { lowBalanceDialogState = false }
            )
        }

        if (eWalletPaymentSuccessDialogState) {
            BaseModalBottomSheet(
                title = "Pembayaran Sukses!",
                body = "Yeay, terima kasih telah bertransaksi",
                positiveButtonTitle = "Lanjut",
                onPositiveButtonClicked = {
                    eWalletPaymentSuccessDialogState = false
                    navigateToHomeScreen()
                }
            )
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
    }

    BackHandler {
        backDialogState = true
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Keranjang",
                onNavigationClicked = { backDialogState = true }
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
                    text = "Item Jajanan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }
            if (checkoutList.isEmpty()) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
                        title = "Belum Ada Item!",
                        description = "Mohon tambahkan item agar bisa melanjutkan proses pesanan"
                    )
                }
            }
            items(
                items = checkoutList.distinctBy { it.id },
                key = { it.id }
            ) { jajanItem ->
                CustomerJajanCheckoutItem(
                    jajan = jajanItem,
                    count = checkoutList.filter { item -> item.id == jajanItem.id }.size,
                    onClick = {},
                    onDecreaseClicked = { checkoutViewModel.removeCheckoutList(jajanItem) },
                    onIncreaseClicked = { checkoutViewModel.addCheckoutList(jajanItem) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .animateItemPlacement()
                )
            }
            if (checkoutList.isNotEmpty()) {
                item {
                    CustomerTotalTransactionFooter(
                        totalPrice = totalPrice.toInt()
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                BaseButton(
                    text = "Tambah Item",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    containerColor = Color(0xFF343434),
                    onClicked = { addItemDialogState = true },
                    enabled = vendorDetailResult is Resource.Success,
                )
            }
            item {
                BaseOutlinedButton(
                    text = "Proses Pesanan",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    enabled = buttonProcessEnabled,
                    onClicked = { paymentDialogState = true }
                )
            }
        }
    }
}