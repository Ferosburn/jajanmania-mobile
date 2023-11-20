package com.tokodizital.jajanmania.customer.ewallet


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.shimmer.BaseTextShimmer
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel



@ExperimentalMaterial3Api
@Composable
fun EWalletScreenCust(
    modifier: Modifier = Modifier,
    eWalletCustViewModel: EWalletCustViewModel = koinViewModel(),
    onNavigationClick: () -> Unit = {},
    navigateToTopUpScreen: () -> Unit = {},
    navigateToNearbyVendorScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {}
) {
    val context = LocalContext.current
    val eWalletCustUiState by eWalletCustViewModel.eWalletCustUiState.collectAsStateWithLifecycle()
    val customerSession = eWalletCustUiState.customerSession
    val customer = eWalletCustUiState.customer

    val refreshTokenResult = eWalletCustUiState.customerRefreshToken
    val isLoadingBalance by eWalletCustViewModel.balanceIsLoading.collectAsState(initial = true)

    var balance by remember { mutableLongStateOf(0L) }

    LaunchedEffect(key1 = Unit) {
        eWalletCustViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            eWalletCustViewModel.getCustomer(
                token = session.accessToken,
                id = session.accountId
            )
        }
    }

    LaunchedEffect(key1 = customer) {
        if (customer is Resource.Success) {
            balance = customer.data.balance
        }
    }

    LaunchedEffect(key1 = customer) {
        if (customer is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            eWalletCustViewModel.refreshToken(
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
            && customer is Resource.Error
        ) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            eWalletCustViewModel.getCustomer(
                token = session.accessToken, id = session.accountId
            )
            eWalletCustViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            eWalletCustViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    val menu = listOf(
        EWalletMenu(
            icon = R.drawable.ic_bayar,
            label = R.string.label_bayar
        ),
        EWalletMenu(
            icon = R.drawable.ic_add,
            label = R.string.label_topUp
        ),
        EWalletMenu(
            icon = R.drawable.ic_history,
            label = R.string.label_history
        )
    )

    val onMenuClicked: (EWalletMenu) -> Unit = {
        when (it.label) {
            R.string.label_bayar -> navigateToNearbyVendorScreen()
            R.string.label_topUp -> navigateToTopUpScreen()
            R.string.label_history -> navigateToTransactionHistoryScreen()
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "E-Wallet", onNavigationClicked = onNavigationClick)
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                EWalletBalanceSection(
                    modifier = Modifier.fillMaxWidth(),
                    onTopUpButtonClick = navigateToTopUpScreen,
                    balance =  balance,
                    isLoadingBalance = isLoadingBalance
                )
            }
            item {
                EWalletMenuSection(
                    menu = menu,
                    modifier = Modifier.fillMaxWidth(),
                    onMenuClicked = onMenuClicked
                )
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun EWalletMenuSection(
    modifier: Modifier = Modifier,
    menu: List<EWalletMenu> = emptyList(),
    onMenuClicked: (EWalletMenu) -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFDD671))
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        menu.forEach {
            EWalletMenuItem(menu = it, onClicked = onMenuClicked)
        }
    }
}

@Composable
fun EWalletMenuItem(
    modifier: Modifier = Modifier,
    menu: EWalletMenu,
    onClicked: (EWalletMenu) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .requiredWidth(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = menu.icon),
            contentDescription = stringResource(id = menu.label),
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFF343434))
                .padding(8.dp)
                .clickable { onClicked(menu) },
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = menu.label),
            fontSize = 14.sp,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)),
            color = Color(0xFF343434),
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun EWalletBalanceSection(
    modifier: Modifier = Modifier,
    onTopUpButtonClick: () -> Unit = {},
    balance: Long = 0L,
    isLoadingBalance: Boolean = false
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_balance),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        color = Color(0xFF343434),
                        shape = RoundedCornerShape(size = 100.dp)
                    )
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Saldo",
                    fontSize = 11.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_medium)
                    ),
                    color = Color(0xFF343434)
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (isLoadingBalance) {
                    BaseTextShimmer(modifier = Modifier.fillMaxWidth(0.5f))
                } else {
                    Text(
                        text = balance.toRupiah(),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_medium)
                        ),
                        color = Color(0xFF343434),
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            BaseButton(
                containerColor = Color(0xFF343434),
                text = "Isi saldo",
                onClicked = onTopUpButtonClick
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEWalletScreenCust() {
    JajanManiaTheme {
        Surface {
            EWalletScreenCust()
        }
    }
}
