package com.tokodizital.jajanmania.vendor.transaction.transfer

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun TransferBankScreen(url: String , modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Transfer Bank")
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            }, update = {
                it.loadUrl(url)
            })
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewTransferBankScreen() {
    JajanManiaTheme {
        Surface {
            TransferBankScreen("www.google.com")
        }
    }
}