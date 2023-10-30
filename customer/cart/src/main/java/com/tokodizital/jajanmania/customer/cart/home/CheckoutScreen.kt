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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.bottomsheet.BaseModalBottomSheet
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerJajanCheckoutItem
import com.tokodizital.jajanmania.ui.components.customer.CustomerTotalTransactionFooter
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigationToAddItemScreen: () -> Unit = {},
    navigationToProcessTransactionScreen: () -> Unit = {},
    listJajanan: List<Jajan> = emptyList(),
    onDecreaseClicked: (Jajan) -> Unit = {},
    onIncreaseClicked: (Jajan) -> Unit = {}
) {

    var backDialogState by remember { mutableStateOf(false) }

    val mappedJajanan = remember(key1 = listJajanan) {
        listJajanan.groupBy { it.id }
            .mapKeys { it.value.first() }
            .mapValues { it.value.count() }
    }

    val totalPrice = remember(key1 = listJajanan) {
        listJajanan.sumOf { it.price }
    }

    val buttonProcessEnabled = remember(key1 = listJajanan) {
        listJajanan.isNotEmpty()
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
            if (listJajanan.isEmpty()) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
                        title = "Belum Ada Item!",
                        description = "Mohon tambahkan item agar bisa melanjutkan proses pesanan"
                    )
                }
            }
            items(items = mappedJajanan.entries.toList(), key = { it.key.id }) {
                CustomerJajanCheckoutItem(
                    jajan = it.key,
                    count = it.value,
                    onClick = {},
                    onDecreaseClicked = onDecreaseClicked,
                    onIncreaseClicked = onIncreaseClicked,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .animateItemPlacement()
                )
            }
            if (listJajanan.isNotEmpty()) {
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
                    onClicked = navigationToAddItemScreen
                )
            }
            item {
                BaseOutlinedButton(
                    text = "Proses Pesanan",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    enabled = buttonProcessEnabled,
                    onClicked = navigationToProcessTransactionScreen
                )
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
                listJajanan = listJajanan
            )
        }
    }
}
