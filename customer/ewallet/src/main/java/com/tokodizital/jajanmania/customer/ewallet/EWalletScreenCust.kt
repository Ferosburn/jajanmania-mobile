package com.tokodizital.jajanmania.customer.ewallet

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.EWalletMenu
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme


@ExperimentalMaterial3Api
@Composable
fun EWalletScreenCust(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
    navigateToTopUpScreen: () -> Unit = {},
    navigateToPaymentScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {}
) {
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
            R.string.label_bayar -> navigateToPaymentScreen()
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
                    balance = 500000L
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
                .clickable {onClicked(menu)},
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
    balance: Long = 0L
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
                Text(
                    text = balance.toRupiah(),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_medium)
                    ),
                    color = Color(0xFF343434),
                )
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
