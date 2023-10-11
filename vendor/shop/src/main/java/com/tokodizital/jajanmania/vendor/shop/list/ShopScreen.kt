package com.tokodizital.jajanmania.vendor.shop.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseExtendedFloatingActionButton
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.components.vendor.JajanItem
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    listJajanan: List<Jajan> = emptyList()
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Kelola Toko")
        },
        floatingActionButton = {
            BaseExtendedFloatingActionButton(
                icon = R.drawable.ic_add,
                text = "Tambah Produk",
                onClick = { }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            stickyHeader {
                Text(
                    text = "List Jajanan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }
            if (listJajanan.isEmpty()) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                        title = "Belum Ada Produk!",
                        description = "Mohon tambahkan produk jajanan yang dijual"
                    )
                }
            }
            items(items = listJajanan, key = { it.id }) {
                JajanItem(jajan = it, onClick = {})
            }
        }
    }
}



@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEmptyShopScreen() {
    JajanManiaTheme {
        Surface {
            ShopScreen()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewFilledShopScreen() {
    JajanManiaTheme {
        Surface {
            val listJajanan: List<Jajan> = remember {
                (1..10).map {
                    Jajan(
                        id = it,
                        vendorId = it,
                        name = "Soto",
                        category = "Makanan Kuah",
                        price = 120000L,
                        image = "https://s3-alpha-sig.figma.com/img/ea05/3764/2661ba0b6775ad6979528ee40a14bf91?Expires=1698019200&Signature=lDl-emDvDcVC4UBNMIT8jVSgUDwMVk--HpFp-Ht4MFuDCqOsaxEztHdJcwxTyZOTgexT0dm2Pemi4mgBHPc2AshwxIgb91RpzxRoTuLAxuGHVuQns~gWBfR2T4gamf4MrUbRBIC5EuMAOYi7DryHgIeQCENX0lv90rQYwmv3LggKDsJEJ1ZP5ZqytJKfN~cI5teLgalDBws1ZBmh3JIgZuo-vqui7xsJ8FwxKHU~3TJsbsOj9tuBXhsV3Ro3XAmAOeDQIsszjyTxXSh40qqzS7xNChg0A6T2qsWilW2~EwZQ0gFDzxwXMnOZSv08s6ipIEyouLMTowlCQewhjMVP5Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                    )
                }
            }
            ShopScreen(
                listJajanan = listJajanan
            )
        }
    }
}

