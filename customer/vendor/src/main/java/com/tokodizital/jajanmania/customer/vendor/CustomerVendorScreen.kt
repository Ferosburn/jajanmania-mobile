package com.tokodizital.jajanmania.customer.vendor

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.customer.CustomerNearbyVendorItem
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CustomerVendorScreen(
    modifier: Modifier = Modifier,
    customerVendorViewModel: CustomerVendorViewModel = koinViewModel(),
    navigateToVendorDetailScreen: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
) {
    val customerVendorUiState by customerVendorViewModel.customerVendorUiState.collectAsState()
    val vendors: List<NearbyVendorResult> = customerVendorUiState.nearbyVendors

    val onItemClick: (NearbyVendorResult) -> Unit = {
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
                CustomerNearbyVendorItem(
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
            CustomerVendorScreen()
        }
    }
}
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun PreviewFilledVendorSelectionScreen() {
    JajanManiaTheme {
        Surface {
            CustomerVendorScreen()
        }
    }
}