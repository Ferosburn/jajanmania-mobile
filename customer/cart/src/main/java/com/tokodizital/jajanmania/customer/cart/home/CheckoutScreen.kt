package com.tokodizital.jajanmania.customer.cart.home

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CartJajanItem
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.bottomsheet.BaseModalBottomSheet
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanCheckoutItem
import com.tokodizital.jajanmania.ui.components.customer.CustomerTotalTransactionFooter
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    checkoutViewModel: CheckoutViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    navigationToAddItemScreen: () -> Unit = {},
    navigationToProcessTransactionScreen: () -> Unit = {},
//    listJajanan: List<Jajan> = emptyList(),
//    onDecreaseClicked: (JajanItem) -> Unit = {},
//    onIncreaseClicked: (JajanItem) -> Unit = {}
) {

    val context = LocalContext.current

    val checkoutUiState by checkoutViewModel.checkoutUiState.collectAsStateWithLifecycle()
    val vendorId = checkoutUiState.vendorId
    val customerSession = checkoutUiState.customerSession
    val customerAccessToken = checkoutUiState.customerAccessToken
    val pageNumber = checkoutUiState.pageNumber
    val pageSize = checkoutUiState.pageSize
    val vendorJajanItemRes = checkoutUiState.vendorJajanItemResource
    val listJajan = checkoutUiState.listCartJajanItem
    val mappedJajan = checkoutUiState.mappedJajan
    val totalPrice = checkoutUiState.totalPrice

    

    val buttonProceedEnabled = checkoutUiState.buttonProceedEnabled

//    val vendorDetailResult = checkoutUiState.vendorDetailResult

    LaunchedEffect(key1 = Unit) {
        checkoutViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            val vendorId = checkoutViewModel.vendorId
            checkoutViewModel.getJajanItems(
                session.accessToken, vendorId, pageNumber, pageSize
            )
        }
    }

    LaunchedEffect(key1 = vendorJajanItemRes) {
        if (vendorJajanItemRes is Resource.Success) {
            val data = vendorJajanItemRes.data
            checkoutViewModel.updateVendorJajanItem(data.jajanItems)
        }
    }
    LaunchedEffect(key1 = listJajan) {
        checkoutViewModel.updateMappedJajan(
            listJajan.groupBy { it.id }
                .mapKeys { it.value.first() }
                .mapValues { it.value.count() }
        )
    }
    LaunchedEffect(key1 = mappedJajan) {
        var totalPrice = 0
        for (item in mappedJajan) {
            totalPrice += (item.key.price * item.value)
        }
        checkoutViewModel.updateTotalPrice(
            totalPrice
        )
    }


//    fun mappedJajan(): Map<CartJajanItem, Int> {
////        val mapJajan = mutableMapOf<String, List<CartJajanItem>>()
//        val curListJajan = listJajan
//        val mapJajananan = curListJajan.groupBy { it.id }
//            .mapKeys { it.value.first() }
//            .mapValues { it.value.count() }
////        listJajanan.forEachIndexed {index, element ->
////            mapJajan[index] = element
////        }
//
//
////                val mappedJajanan = remember(key1 = curListJajan) {
////                    curListJajan.groupBy { it.id }
////                        .mapKeys { it.value.first() }
////                        .mapValues { it.value.count() }
////                }
//
//
//        return mapJajananan
//    }

    fun calculatePrice(items: Map<CartJajanItem, Int>): Int {
        var total = 0
        for (item in items) {
            total += item.key.price
        }
        return total
    }

//    LaunchedEffect(key1 = vendorDetailResult) {
//        if (vendorDetailResult is Resource.Success) {
//            val jajanItems = vendorDetailResult.data.jajanItems
//            val convertJajanItem = mutableListOf<JajanItem>()
//            for (item in jajanItems) {
//                val newJajan = JajanItem(
//                    id = item.id,
//                    name = item.name,
//                    price = item.price.toInt(),
//                    imageUrl = item.imageUrl,
//                    categoryId = item.category
//                )
//                convertJajanItem.add(newJajan)
//            }
//            checkoutViewModel.updateJajanItems(convertJajanItem)
//        }
//    }

//    LaunchedEffect(key1 = vendorJajanItems) {
//        listJajan = vendorJajanItems.toMutableList()
//    }


    var backDialogState by remember { mutableStateOf(false) }

//    val mappedJajanan = remember(key1 = listJajan) {
//        listJajan.groupBy { it.id }
//            .mapKeys { it.value.first() }
//            .mapValues { it.value.count() }
//    }

//    val totalPrice = remember(key1 = listJajan) {
//        listJajan.sumOf { it.price }
//    }
//
//    val buttonProcessEnabled = remember(key1 = listJajan) {
//        listJajan.isNotEmpty()
//    }


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

    BackHandler {
        backDialogState = true
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Keranjang",
                onNavigationClicked = { backDialogState = true },
                actions = {

                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.End
        ) {
            if (vendorJajanItemRes is Resource.Success) {
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
                if (listJajan.isEmpty()) {
                    item {
                        EmptyContentState(
                            modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
                            title = "Belum Ada Item!",
                            description = "Mohon tambahkan item agar bisa melanjutkan proses pesanan"
                        )
                    }
                } else {

                    items(items = mappedJajan.entries.toList(), key = { it.key.id } ) {
                        CustomerJajanCheckoutItem(
                            jajan = it.key,
                            count = it.value,
                            onClick = {},
                            onDecreaseClicked = {
                                val jajanan = listJajan.toMutableList()
                                jajanan.remove(it)
                                checkoutViewModel.updateVendorJajanItem(jajanan)
                            },
                            onIncreaseClicked = {
                                val jajanan = listJajan.toMutableList()
                                jajanan.add(it)
                                checkoutViewModel.updateVendorJajanItem(jajanan)
                            },
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .animateItemPlacement()
                        )

                    }
                    item {
                        CustomerTotalTransactionFooter(
                            totalPrice = totalPrice
                        )
                    }

//                    item {
////                        val mappedJajanan = remember(key1 = listJajan) {
////                            listJajan.groupBy { it.id }
////                                .mapKeys { it.value.first() }
////                                .mapValues { it.value.count() }
////                        }
////                        LazyColumn {
////
////                        }
//
//
//
////                        listJajan.map { jajan ->
////                            CustomerJajanCheckoutItem(
////                                jajan = jajan,
////                                count = 0,
////                                onClick = {},
////                                onDecreaseClicked = {},
////                                onIncreaseClicked = {},
////                                modifier = Modifier
////                                    .padding(horizontal = 16.dp)
////                                    .animateItemPlacement()
////                            )
////                        }
//                    }
                }
                


//                item {
//                    val remoteJajan = vendorDetailResult.data.jajanItems
//                    val convertListJajan = mutableListOf<JajanItem>()
//                    for (item in remoteJajan) {
//                        val newJajan = JajanItem(
//                            id = item.id,
//                            name = item.name,
//                            price = item.price.toInt(),
//                            imageUrl = item.imageUrl,
//                            categoryId = item.category
//                        )
//                        convertListJajan.add(newJajan)
//                    }
//                    convertListJajan.map {
//                        CustomerJajanCheckoutItem(
//                            jajan = it,
//                            count = 1,
//                            onClick = {},
//                            onDecreaseClicked = {
////                                val jaja
//                            },
//                            onIncreaseClicked = {
//
//                            }
//                        )
//                    }
//                }

                
               

//                val mappedJajanan = remember(key1 = convertListJajan) {
//                    convertListJajan.groupBy { it.id }
//                        .mapKeys { it.value.first() }
//                        .mapValues { it.value.count() }
//                }
//                items(items = mappedJajanan.entries.toList(), key = { it.key.id }) {
//                    CustomerJajanCheckoutItem(
//                        jajan = it.key,
//                        count = it.value,
//                        onClick = {},
//                        onDecreaseClicked = {
//                            val jajanan = listJajan.toMutableList()
//                            jajanan.remove(it)
//                            listJajan = jajanan
//                        },
//                        onIncreaseClicked = {
//                            val jajanan = listJajan.toMutableList()
//                            jajanan.add(it)
//                            listJajan = jajanan
//                        },
//                        modifier = Modifier
//                            .padding(horizontal = 16.dp)
//                            .animateItemPlacement()
//                    )
//                }
//                if (listJajan.isNotEmpty()) {
//                    item {
//                        CustomerTotalTransactionFooter(
//                            totalPrice = totalPrice.toInt()
//                        )
//                    }
//                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
//                item {
//                    BaseButton(
//                        text = "Tambah Item",
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        containerColor = Color(0xFF343434),
//                        onClicked = navigationToAddItemScreen
//                    )
//                }
                item {
                    BaseOutlinedButton(
                        text = "Proses Pesanan",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        enabled = mappedJajan.size > 0,
//                        enabled = true,
                        onClicked = navigationToProcessTransactionScreen
                    )
                }
            } else {

            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewEmptyCheckoutScreen() {
    JajanManiaTheme {
        Surface {
            CheckoutScreen()
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewFilledCheckoutScreen() {
    val listJajanan: List<Jajan> by remember {
        mutableStateOf(listOf(
            Jajan(
                id = 1,
                vendorId = 1,
                name = "Soto",
                category = "Makanan Kuah",
                price = 120000L,
                image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
            ),
            Jajan(
                id = 2,
                vendorId = 1,
                name = "Batagor Isi 7",
                category = "Tahu Isi",
                price = 10000L,
                image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
            ),
            Jajan(
                id = 1,
                vendorId = 1,
                name = "Soto",
                category = "Makanan Kuah",
                price = 120000L,
                image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
            )
        ))
    }
    JajanManiaTheme {
        Surface {
            CheckoutScreen(
//                listJajanan = listJajanan
            )
        }
    }
}
