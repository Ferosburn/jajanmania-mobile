package com.tokodizital.jajanmania.customer.payment

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Vendor
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.components.vendor.VendorItem
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun VendorSelectionScreen(
    modifier: Modifier = Modifier,
    navigateToVendorDetailScreen: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
    vendors: List<Vendor> = emptyList()
) {
    val onItemClick: (Vendor) -> Unit = {
        navigateToVendorDetailScreen()
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Daftar Penjual",
                actions = {

                },
                onNavigationClicked = onNavigationClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            stickyHeader {
                Text(
                    text = "Penjual Di Dekat Anda",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }
            if (vendors.isEmpty()) {
                item {
                    EmptyContentState(
                        modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                        title = "Yah, Tidak Ada Penjual : (",
                        description = "Maaf kak, saat ini tidak ada penjual yang dekat"
                    )
                }
            }
            items(items = vendors, key = {it.id}) {
                VendorItem(
                    vendor = it,
                    onClick = onItemClick,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

            }
        }

    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun PreviewEmptyVendorSelectionScreen() {
    JajanManiaTheme {
        Surface {
            VendorSelectionScreen()
        }
    }
}
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun PreviewFilledVendorSelectionScreen() {
    val listVendor: List<Vendor> = remember {
        (1..4).map {
            Vendor(
                id = "fbe68aec-8119-42a9-a820-ef7e6ebf2f20$it",
                fullname = "Vendor Name",
                gender = "MALE",
                address = "Jl Melati no 9",
                username = "dummyUsername",
                email = "dummyemail@email.com",
                balance = 0,
                experience = 0,
                jajanImage = "",
                jajanName = "Pisang Keju Pak Eko $it",
                jajanDescription = "Spesialis pisang keju di kota Kotok $it",
                status = "ON",
                lastLat = 0.0,
                lastLng = 1.0
            )
        }
    }
    JajanManiaTheme {
        Surface {
            VendorSelectionScreen(vendors = listVendor)
        }
    }
}