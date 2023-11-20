package com.tokodizital.jajanmania.customer.vendor.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.customer.CustomerVendorJajanItem
import com.tokodizital.jajanmania.ui.components.shimmer.BaseBoxShimmer
import com.tokodizital.jajanmania.ui.components.shimmer.BaseTextShimmer
import com.tokodizital.jajanmania.ui.components.state.EmptyContentState
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun CustomerVendorDetailScreen(
    modifier: Modifier = Modifier,
    customerVendorDetailViewModel: CustomerVendorDetailViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToCheckoutScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
) {
    val context = LocalContext.current
    val customerVendorDetailUiState by customerVendorDetailViewModel.customerVendorDetailUiState.collectAsState()
    val customerSession = customerVendorDetailUiState.customerSession
    val vendorDetailResult = customerVendorDetailUiState.vendorDetailResult
    val refreshTokenResult = customerVendorDetailUiState.refreshTokenResult

    LaunchedEffect(key1 = Unit) {
        customerVendorDetailViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            val vendorId = customerVendorDetailViewModel.vendorId
            customerVendorDetailViewModel.getVendorDetail(
                vendorId, session.accessToken
            )
        }
    }

    LaunchedEffect(key1 = vendorDetailResult) {
        if (vendorDetailResult is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            customerVendorDetailViewModel.refreshToken(
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
            val vendorId = customerVendorDetailViewModel.vendorId
            customerVendorDetailViewModel.getVendorDetail(
                vendorId, session.accessToken
            )
            customerVendorDetailViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            customerVendorDetailViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

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
                    enabled = vendorDetailResult is Resource.Success && vendorDetailResult.data.jajanItems.isNotEmpty()
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
            if (vendorDetailResult is Resource.Success) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                        ) {
                            AsyncImage(
                                model = vendorDetailResult.data.image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .fillMaxSize(),
                                placeholder = painterResource(id = R.drawable.ic_jajan_mania_48),
                                error = painterResource(id = R.drawable.ic_jajan_mania_48)
                            )
                        }
                        Text(
                            text = vendorDetailResult.data.name,
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
                            text = vendorDetailResult.data.description,
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
                        if (vendorDetailResult.data.jajanItems.isEmpty()) {
                            EmptyContentState(
                                modifier = Modifier.padding(top = 46.dp),
                                title = "Yah, Tidak Ada Jajan : (",
                                description = "Penjual belum menambahkan produknya nih.."
                            )
                        }
                        vendorDetailResult.data.jajanItems.map { jajan ->
                            CustomerVendorJajanItem(jajan = jajan)
                        }
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BaseTextShimmer(
                            modifier = Modifier
                                .size(120.dp, 120.dp)
                                .clip(CircleShape)
                        )
                        BaseTextShimmer(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .clip(CircleShape)
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
                        BaseTextShimmer(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        BaseTextShimmer(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .clip(CircleShape)
                        )
                    }
                }
                item {
                    Column {
                        Text(
                            text = stringResource(R.string.title_product_list),
                            style = MaterialTheme.typography.titleMedium
                        )
                        (1..5).map {
                            BaseBoxShimmer()
                            Spacer(modifier = Modifier.height(4.dp))
                        }
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
            CustomerVendorDetailScreen()
        }
    }
}