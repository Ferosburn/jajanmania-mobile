package com.tokodizital.jajanmania.customer.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
    navigateToPaymentDetailScreen: () -> Unit = {}
) {
    Scaffold(
        topBar = { DetailTopAppBar(title = "Bayar", onNavigationClicked = onNavigationClick) },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp, 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
            var value by remember { mutableStateOf("") }
            Text(text = "Masukkan Kode Pembayaran", style = MaterialTheme.typography.titleMedium)
            BaseOutlinedTextField(
                value = value,
                onValueChanged = { value = it },
                label = "Kode Pembayaran",
                placeholder = "Masukkan kode pembayaran",
                modifier = Modifier.fillMaxWidth()
            )
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                BaseButton(text = "Masukkan", enabled = value.isNotEmpty(), onClicked = navigateToPaymentDetailScreen)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PaymentScreenPreview() {
    JajanManiaTheme {
        Surface {
            PaymentScreen()
        }
    }
}