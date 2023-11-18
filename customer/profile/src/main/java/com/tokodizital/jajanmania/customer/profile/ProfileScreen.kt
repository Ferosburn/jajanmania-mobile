package com.tokodizital.jajanmania.customer.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.info.InfoDialog
import com.maxkeppeler.sheets.info.models.InfoBody
import com.maxkeppeler.sheets.info.models.InfoSelection
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseExtendedFloatingActionButton
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.components.cards.ProfileInfoCard
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
    navigateToEditProfileScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {},
    navigateToMySubscriptionScreen: () -> Unit = {},
    navigateToLevelScreen: () -> Unit = {},
    navigateToTermAndConditionScreen: () -> Unit = {},
    navigateToHelpCenterScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {}
) {
    val context = LocalContext.current
    val logoutDialog = rememberUseCaseState()

    val profileUiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()
    val customerSession = profileUiState.customerSession
    val customer = profileUiState.customer
    val refreshTokenResult = profileUiState.customerRefreshToken
    val logoutResult = profileUiState.customerLogoutResult

    var profileName by remember { mutableStateOf("...") }
    var profileUsername by remember { mutableStateOf("...") }
    var profileEmail by remember { mutableStateOf("...") }
    var profileLevel by remember { mutableStateOf("...") }

    val showLogoutDialog: () -> Unit = {
        logoutDialog.show()
    }

    LaunchedEffect(key1 = Unit) {
        profileViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            profileViewModel.getCustomer(
                token = session.accessToken,
                id = session.accountId
            )
        }
    }

    LaunchedEffect(key1 = customer) {
        if (customer is Resource.Success) {
            val data = customer.data
            profileName= data.fullName
            profileEmail = data.email
            profileUsername = "@" + data.username
            profileLevel = "LEVEL 1"
        }
    }
    LaunchedEffect(key1 = customer) {
        if (customer is Resource.Error && customerSession is Resource.Success) {
            val session = customerSession.data
            profileViewModel.refreshToken(
                accountId = session.accountId,
                accountType = session.accountType,
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                expiredAt = session.expiredAt,
                firebaseToken = session.firebaseToken,
            )
        }
    }

    LaunchedEffect(key1 = refreshTokenResult) {
        if (refreshTokenResult is Resource.Success
            && customer is Resource.Error
        ) {
            Toast.makeText(context, "Ada kesalahan aplikasi", Toast.LENGTH_SHORT).show()
        } else if (refreshTokenResult is Resource.Success) {
            val session = refreshTokenResult.data
            profileViewModel.getCustomer(
                token = session.accessToken, id = session.accountId
            )
            profileViewModel.updateCustomerSession(session)
        }
        if (refreshTokenResult is Resource.Error) {
            profileViewModel.deleteCustomerSession()
            navigateToLoginScreen()
        }
    }

    LaunchedEffect(key1 = logoutResult) {
        if (logoutResult is Resource.Success<*>) {
            profileViewModel.updateCustomerSession(CustomerRefreshTokenResult())
            navigateToLoginScreen()
        }
        if (logoutResult is Resource.Error<*>) {
            Toast.makeText(context, logoutResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    InfoDialog(
        state = logoutDialog,
        selection = InfoSelection(onPositiveClick = profileViewModel::logout,),
        body = InfoBody.Default(
            bodyText = "Apakah yakin ingin keluar?"
        )
    )

    Scaffold(
        topBar = { DetailTopAppBar(title = "Profile Customer", onNavigationClicked = onNavigationClick) },
        modifier = modifier
    ) { paddingValues ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
            ){
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1F)
                    ) {
                        ProfileInfoCard(
                            profileName,
                            profileUsername,
                            profileEmail,
                            profileLevel
                        )
                    }
                    BaseExtendedFloatingActionButton(
                        modifier = Modifier.size(50.dp),
                        icon = R.drawable.ic_edit,
                        text = "",
                        onClick = navigateToEditProfileScreen
                    )
                }

                Box(
                    modifier = Modifier
                ) {
                    Column {
                        Divider(thickness = 8.dp)

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_history),
                            menuTitle = "Riwayat Transaksi",
                            menuDescription = "Lihat riwayat transaksi",
                            showEnter = true,
                            onClick = navigateToTransactionHistoryScreen
                        )

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_store),
                            menuTitle = "Langgananku",
                            menuDescription = "Atur vendor langgananku",
                            showEnter = true,
                            onClick = navigateToMySubscriptionScreen
                        )

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_star),
                            menuTitle = "Status Pelanggan",
                            menuDescription = profileLevel,
                            showEnter = true,
                            onClick = navigateToLevelScreen

                        )

                        Divider(thickness = 8.dp)

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_event_note),
                            menuTitle = "Ketentuan dan Privasi",
                            menuDescription = "",
                            showEnter = true ,
                            onClick = navigateToTermAndConditionScreen
                        )

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_help),
                            menuTitle = "Pusat Bantuan",
                            menuDescription = "",
                            showEnter = true,
                            onClick = navigateToHelpCenterScreen
                        )
                        MenuButton(
                            imageVector = Icons.Rounded.ExitToApp,
                            menuTitle = "Keluar",
                            menuDescription = "",
                            showEnter = false,
                            onClick = showLogoutDialog
                        )
                    }
                }
            }
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