package com.tokodizital.jajanmania.customer.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.YellowButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun WelcomeScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(all = 20.dp)
        .background(
            Color.White
        ),
        contentAlignment = Alignment.TopCenter){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(150.dp))

            YellowButton(text = "Login Customer") {
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Atau", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(20.dp))

            YellowButton(text = "Login Vendor") {
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    JajanManiaTheme() {
        Surface {
            WelcomeScreen()
        }
    }
}
