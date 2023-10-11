package com.tokodizital.jajanmania.vendor.shop.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseExtendedFloatingActionButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.R

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ShopScreen(
    modifier: Modifier = Modifier
) {

    val listJajanan = remember {
        emptyList<String>()
    }

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
            contentPadding = PaddingValues(start = 20.dp, top = 16.dp, end = 20.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            stickyHeader {
                Text(
                    text = "List Jajanan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                )
            }
            if (listJajanan.isEmpty()) {
                item {
                    EmptyShopContent(
                        modifier = Modifier.padding(top = 62.dp)
                    )
                }
            }
        }
    }
}



@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewShopScreen() {
    JajanManiaTheme {
        Surface {
            ShopScreen()
        }
    }
}
