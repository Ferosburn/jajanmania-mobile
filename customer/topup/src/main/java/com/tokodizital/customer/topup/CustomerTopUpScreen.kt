package com.tokodizital.customer.topup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun CustomerTopUpScreen(
    modifier: Modifier = Modifier,
    customerTopUpViewModel: CustomerTopUpViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToHomeScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
) {

    val customerTopUpUiState by customerTopUpViewModel.customerTopUpUiState.collectAsState()
    val customerSession = customerTopUpUiState.customerSession
    val topUp = customerTopUpUiState.topUpResult

    var token by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var xenditUrl by remember { mutableStateOf("") }

    val listOfNominal: List<Long> = remember {
        listOf(20_000L, 50_000L, 100_000L, 200_000L, 300_000L, 500_000L)
    }
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(key1 = Unit) {
        customerTopUpViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            token = session.accessToken
            userId = session.accountId
            customerTopUpViewModel.getCustomer(
                token = token,
                id = userId,
            )
        }
    }

    LaunchedEffect(key1 = topUp) {
        if (topUp is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            customerTopUpViewModel.refreshToken(
                accountId = session.accountId,
                accountType = session.accountType,
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                expiredAt = session.expiredAt,
                firebaseToken = session.firebaseToken,
            )
        }
    }

    LaunchedEffect(key1 = topUp) {
        if (topUp is Resource.Success) {
            xenditUrl = topUp.data.redirectUrl
            println(xenditUrl)
            uriHandler.openUri(xenditUrl)
        }
    }

    val onTopUpButtonClick: () -> Unit = {
        // TODO: send the user id and nominal to server and get the xendit URL,
        // then set the URL to xenditURL variable
        navigateToHomeScreen()
    }


    val onNominalButtonClick: (Long) -> Unit = { value ->
        amount = value.toString()
    }

    Scaffold(
        modifier = modifier,
        topBar = { DetailTopAppBar(title = "Isi Saldo", onNavigationClicked = navigateUp) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                BaseButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Top Up",
                    onClicked = {
                        customerTopUpViewModel.topUp(
                            token, userId, amount
                        )
                        onTopUpButtonClick
                    },
                    enabled = (if (amount.isNotBlank()) amount.toLong() >= 5000L else amount.isNotBlank())
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp, 24.dp)
        ) {
            Text(text = "Pilih Nominal", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOfNominal) { nominal ->
                    BaseOutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = nominal.toRupiah(),
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        onClicked = { onNominalButtonClick(nominal) }
                    )
                }
            }
            Spacer(modifier = modifier.height(16.dp))
            BaseOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = amount,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                onValueChanged = { value ->
                    if (value.isNotEmpty()) {
                        amount = value.filter { it.isDigit() }
                    }
                },
                prefix = { Text(text = "Rp") },
                label = "Nominal",
                placeholder = "0"
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewCustomerTopUpScreen() {
    JajanManiaTheme {
        Surface {
            CustomerTopUpScreen()
        }
    }
}