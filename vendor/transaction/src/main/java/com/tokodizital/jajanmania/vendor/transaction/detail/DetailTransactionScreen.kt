package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun DetailTransactionScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Transaksi",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->

    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailTransactionScreen() {
    JajanManiaTheme {
        Surface {
            DetailTransactionScreen()
        }
    }
}