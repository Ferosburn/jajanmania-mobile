package com.tokodizital.jajanmania.customer.auth

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.Red
import com.tokodizital.jajanmania.ui.theme.Yellow
import kotlinx.coroutines.delay


@Composable
fun SplashScreen() {
    val alpha = remember {
        Animatable(0f)
    }

    var progress by remember { mutableStateOf(0.1f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    LaunchedEffect(key1 = true){
        alpha.animateTo(1f,
        animationSpec = tween(3000)
        )
        if (progress < 1f) progress += 0.8f
        delay(1600)

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp)
            .background(
                Color.White
            ),
        contentAlignment = Alignment.TopCenter) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.alpha(alpha.value),
                painter = painterResource(id = com.tokodizital.jajanmania.ui.R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "by",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Image(
                    modifier = Modifier
                        .alpha(alpha.value)
                        .size(80.dp),
                    painter = painterResource(id = com.tokodizital.jajanmania.ui.R.drawable.logo_toko_dizital),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

            }

            Spacer(modifier = Modifier.height(80.dp))
            
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                LinearProgressIndicator(
                    progress = animatedProgress,
                trackColor = Yellow,
                color = Red)

                Spacer(modifier = Modifier.height(90.dp))

                Text(
                    text = "in collaboration with",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )

                Image(
                    modifier = Modifier
                        .alpha(alpha.value)
                        .size(150.dp),
                    painter = painterResource(id = com.tokodizital.jajanmania.ui.R.drawable.logo_goto),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    JajanManiaTheme() {
        Surface {
            SplashScreen()
        }
    }
}