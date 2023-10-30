package com.tokodizital.customer.topup

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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.common.utils.toRupiah
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun CustomerTopUpScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateToHomeScreen: () -> Unit = {},
) {
    var nominal by remember { mutableStateOf("") }
    var xenditUrl by remember { mutableStateOf("https://checkout-staging.xendit.co/v2/653f5520a28e64aad3b36253") }
    val listOfNominal: List<Long> = remember {
        listOf(20_000L, 50_000L, 100_000L, 200_000L, 300_000L, 500_000L)
    }
    val uriHandler = LocalUriHandler.current

    val onNominalButtonClick: (Long) -> Unit = { value ->
        nominal = value.toString()
    }

    val onTopUpButtonClick: () -> Unit = {
        // TODO: send the user id and nominal to server and get the xendit URL,
        //       then set the URL to xenditURL variable
        navigateToHomeScreen()
        uriHandler.openUri(xenditUrl)
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
                    onClicked = onTopUpButtonClick,
                    enabled = (if (nominal.isNotBlank()) nominal.toLong() >= 5000L else nominal.isNotBlank())
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
                value = nominal,
                onValueChanged = { value ->
                    if (value.isNotEmpty()) {
                        nominal = value.filter { it.isDigit() }
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