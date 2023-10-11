package com.tokodizital.jajanmania.customer.ewallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EWalletSettingScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "E-Wallet") })},
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column {
                Box(modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                ) {
                    Text(text = "Pengaturan")
                }
                MenuButton(
                    imageVector = ImageVector.vectorResource(com.tokodizital.jajanmania.ui.R.drawable.ic_password),
                    menuTitle = "Ubah PIN",
                    menuDescription = "Ubah PIN untuk meningkatkan keamanan",
                    showEnter = true
                ) {
                    
                }
                MenuButton(
                    imageVector = ImageVector.vectorResource(com.tokodizital.jajanmania.ui.R.drawable.ic_lock_reset),
                    menuTitle = "Lupa PIN",
                    menuDescription = "",
                    showEnter = true
                ) {

                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEWalletSettingScreen() {
    JajanManiaTheme {
        Surface {
            EWalletSettingScreen()
        }
    }
}