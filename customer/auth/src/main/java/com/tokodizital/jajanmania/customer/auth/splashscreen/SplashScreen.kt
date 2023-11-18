package com.tokodizital.jajanmania.customer.auth.splashscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    splashScreenViewModel: SplashScreenViewModel = koinViewModel(),
    navigateToLoginOptionScreen: () -> Unit = {},
    navigateToCustomerHomeScreen: () -> Unit = {},
    navigateToVendorHomeScreen: () -> Unit = {},
) {
    val splashScreenUiState by splashScreenViewModel.splashScreenUiState.collectAsState()
    val customerSession = splashScreenUiState.customerSession
    val customerRefreshTokenResult = splashScreenUiState.customerRefreshTokenResult
    val vendorSession = splashScreenUiState.vendorSession
    val vendorRefreshTokenResult = splashScreenUiState.vendorRefreshTokenResult
    var progress by remember { mutableFloatStateOf(0.0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "loading"
    )

    LaunchedEffect(key1 = Unit) {
        splashScreenViewModel.getCustomerSession()
        splashScreenViewModel.getVendorSession()
        progress += 0.3f
        delay(1000L)
    }

    LaunchedEffect(key1 = customerSession, key2 = vendorSession) {
        progress += 0.3f
        delay(1000L)
        if (customerSession is Resource.Success && customerSession.data.accessToken.isNotEmpty()) {
            val session = customerSession.data
            splashScreenViewModel.customerRefreshToken(
                accountId = session.accountId,
                accountType = session.accountType,
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                expiredAt = session.expiredAt,
                firebaseToken = session.firebaseToken,
            )
        } else if (vendorSession is Resource.Success && vendorSession.data.accessToken.isNotEmpty()) {
            val session = vendorSession.data
            splashScreenViewModel.vendorRefreshToken(
                accountId = session.accountId,
                accountType = session.accountType,
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                expiredAt = session.expiredAt,
            )
        }
        if ((customerSession is Resource.Success && customerSession.data.accessToken.isEmpty()) || (vendorSession is Resource.Success && vendorSession.data.accessToken.isEmpty())) {
            navigateToLoginOptionScreen()
        }
    }

    LaunchedEffect(key1 = customerRefreshTokenResult) {
        progress = 1f
        if (customerRefreshTokenResult is Resource.Success && customerRefreshTokenResult.data.accessToken.isNotEmpty()) {
            val session = customerRefreshTokenResult.data
            splashScreenViewModel.updateCustomerSession(session)
            navigateToCustomerHomeScreen()
        } else if (customerRefreshTokenResult is Resource.Error) {
            navigateToLoginOptionScreen()
        }
    }

    LaunchedEffect(key1 = vendorRefreshTokenResult) {
        progress = 1f
        delay(1000L)
        if (vendorRefreshTokenResult is Resource.Success && vendorRefreshTokenResult.data.accessToken.isNotEmpty()) {
            val session = vendorRefreshTokenResult.data
            splashScreenViewModel.updateVendorSession(session)
            navigateToVendorHomeScreen()
        } else if (vendorRefreshTokenResult is Resource.Error) {
            navigateToLoginOptionScreen()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(id = com.tokodizital.jajanmania.ui.R.drawable.logo_jajan_mania),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(100.dp))
            LinearProgressIndicator(
                progress = animatedProgress,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    JajanManiaTheme() {
        Surface {
            SplashScreen()
        }
    }
}