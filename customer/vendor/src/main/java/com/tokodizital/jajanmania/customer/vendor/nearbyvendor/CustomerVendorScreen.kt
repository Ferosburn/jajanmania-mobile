package com.tokodizital.jajanmania.customer.vendor.nearbyvendor

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.customer.CustomerNearbyVendorItem
import com.tokodizital.jajanmania.ui.components.shimmer.BaseBoxShimmer
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.koinViewModel

@FlowPreview
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CustomerVendorScreen(
    modifier: Modifier = Modifier,
    customerVendorViewModel: CustomerVendorViewModel = koinViewModel(),
    navigateToVendorDetailScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
) {

    val context = LocalContext.current
    val customerVendorUiState by customerVendorViewModel.customerVendorUiState.collectAsState()
    val latitude = customerVendorUiState.latitude
    val longitude = customerVendorUiState.longitude
    val pageNumber = customerVendorUiState.pageNumber
    val customerSession = customerVendorUiState.customerSession
    val refreshTokenResult = customerVendorUiState.refreshTokenResult
    val nearbyVendorsResult = customerVendorUiState.nearbyVendorsResult

    val onItemClick: (NearbyVendorResult) -> Unit = {
        navigateToVendorDetailScreen()
    }

    LaunchedEffect(key1 = Unit) {
        customerVendorViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            customerVendorViewModel.getNearbyVendors(
                latitude, longitude, pageNumber, session.accessToken
            )
        }
    }

    LaunchedEffect(key1 = nearbyVendorsResult) {
        if (nearbyVendorsResult is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            customerVendorViewModel.refreshToken(
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
        if (refreshTokenResult is Resource.Success && nearbyVendorsResult is Resource.Error) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            customerVendorViewModel.getNearbyVendors(
                latitude, longitude, pageNumber, session.accessToken
            )
            customerVendorViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            customerVendorViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Daftar Penjual",
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
            if (nearbyVendorsResult is Resource.Loading) {
                items(5) {
                    BaseBoxShimmer(modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            if (nearbyVendorsResult is Resource.Success) {
                if (nearbyVendorsResult.data.isEmpty()) {
                    item {
                        EmptyContentState(
                            modifier = Modifier.padding(start = 20.dp, top = 46.dp, end = 20.dp),
                            title = "Yah, Tidak Ada Penjual : (",
                            description = "Maaf kak, saat ini tidak ada penjual yang dekat"
                        )
                    }
                }
                items(items = nearbyVendorsResult.data, key = { it.id }) {
                    CustomerNearbyVendorItem(
                        vendor = it,
                        onClick = onItemClick,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }

    }
}

@FlowPreview
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