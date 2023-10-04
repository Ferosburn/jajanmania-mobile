package com.tokodizital.jajanmania.customer.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Profile Customer") })
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
                .padding(all = 32.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Welcome to customer!")
        }
    }
}



@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewProfileScreen() {
    JajanManiaTheme {
        Surface {
            ProfileScreen()
        }
    }
}