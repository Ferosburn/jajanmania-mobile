package com.tokodizital.jajanmania.vendor.shop.manage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.components.buttons.MenuButtonSwitch
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ManageScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigateManageProduct: () -> Unit = {},
) {
    var isSwitchChecked by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Kelola Toko",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            stickyHeader {
                Text(
                    text = "Toko",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 16.dp)
                )
            }
            item {
                Box(
                    modifier = Modifier
                ) {
                    Column {
                        MenuButtonSwitch(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_setting),
                            menuTitle = "Aktifkan/Non Aktifkan Penjualan",
                            menuDescription = "Silahkan aktifkan jika kamu sedang berjualan agar pembeli dapat menemukanmu",
                            isChecked = isSwitchChecked,
                            onSwitchChanged = { newValue -> isSwitchChecked = newValue },
                            onClick = {}
                        )

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_archive),
                            menuTitle = "Kelola Jajanan",
                            menuDescription = "Kelola nama dan harga produk yang dijual",
                            showEnter = true,
                            onClick = navigateManageProduct
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewManageScreen() {
    JajanManiaTheme {
        Surface {
            ManageScreen()
        }
    }
}

