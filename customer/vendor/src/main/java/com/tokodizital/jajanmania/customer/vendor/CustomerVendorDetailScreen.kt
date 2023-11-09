package com.tokodizital.jajanmania.customer.vendor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerVendorJajanItem
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun CustomerVendorDetailScreen(
    modifier: Modifier = Modifier,
    jajanList: List<Jajan> = emptyList(),
    nearbyVendor: NearbyVendorResult,
    navigateUp: () -> Unit = {},
    navigateToCheckoutScreen: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = stringResource(R.string.title_vendor_detail),
                onNavigationClicked = navigateUp
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                BaseButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.label_buy),
                    onClicked = navigateToCheckoutScreen,
                    enabled = jajanList.isNotEmpty()
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp, 24.dp),
            modifier = modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = nearbyVendor.name,
                        modifier = Modifier
                            .size(120.dp, 120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = nearbyVendor.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.title_vendor_description),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = nearbyVendor.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            item {
                Column {
                    Text(
                        text = stringResource(R.string.title_product_list),
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (jajanList.isEmpty()) {
                        EmptyContentState(
                            modifier = Modifier.padding(top = 46.dp),
                            title = "Yah, Tidak Ada Jajan : (",
                            description = "Penjual belum menambahkan produknya nih.."
                        )
                    }
                    jajanList.map { jajan ->
                        CustomerVendorJajanItem(jajan = jajan)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCustomerVendorDetailScreen() {
    JajanManiaTheme {
        Surface {
            val vendor = remember {
                NearbyVendorResult(
                    id ="",
                    name = "Batagor Bang Tigor",
                    description = "Batagor renyah di luar, lembut di dalam, mantap bumbunya"
                )
            }
            val list: List<Jajan> = remember {
                (1..7).map {
                    Jajan(
                        id = it,
                        vendorId = 12317414,
                        name = "Es Teh",
                        category = "Minuman Dingin",
                        price = 3000L,
                        image = ""
                    )
                }
            }
            CustomerVendorDetailScreen(
                nearbyVendor = vendor,
                jajanList = list
            )
        }
    }
}
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCustomerVendorDetailScreenEmptyProduct() {
    val vendor = remember {
        NearbyVendorResult(
            id ="",
            name = "Batagor Bang Tigor",
            description = "Batagor renyah di luar, lembut di dalam, mantap bumbunya"
        )
    }
    val list: List<Jajan> = remember {
        emptyList()
    }

    JajanManiaTheme {
        Surface {
            CustomerVendorDetailScreen(
                nearbyVendor = vendor,
                jajanList = list
            )
        }
    }
}