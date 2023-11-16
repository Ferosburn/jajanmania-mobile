package com.tokodizital.jajanmania.vendor.account.profil

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
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
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.account.components.ProfileInfoCard
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    accountViewModel: AccountViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    navigateToEditAccountScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {},
    navigateToManageShopScreen: () -> Unit = {},
    navigateToLevelScreen: () -> Unit = {},
    navigateToTermAndConditionScreen: () -> Unit = {},
    navigateToHelpCenterScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {}
) {

    val context = LocalContext.current
    val logoutDialog = rememberUseCaseState()

    val accountUiState by accountViewModel.accountUiState.collectAsStateWithLifecycle()
    val vendorSession = accountUiState.vendorSession
    val vendor = accountUiState.vendor
    val logoutResult = accountUiState.logoutResult

    var jajanName by remember { mutableStateOf("...") }
    var vendorName by remember { mutableStateOf("...") }
    var vendorUsername by remember { mutableStateOf("...") }
    var vendorLevel by remember { mutableStateOf("...") }
    var imageUrl by remember { mutableStateOf("") }

    val showLogoutDialog: () -> Unit = {
        logoutDialog.show()
    }

    LaunchedEffect(key1 = Unit) {
        accountViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            accountViewModel.getVendor(
                token = session.accessToken,
                id = session.accountId
            )
        }
    }

    LaunchedEffect(key1 = vendor) {
        if (vendor is Resource.Success) {
            val data = vendor.data
            jajanName = data.jajanName
            vendorName = data.fullName
            vendorUsername = "@" + data.username
            vendorLevel = "LEVEL 1"
            imageUrl = data.jajanImageUrl
        }
    }

    LaunchedEffect(key1 = logoutResult) {
        if (logoutResult is Resource.Success) {
            accountViewModel.updateVendorSession(RefreshTokenResult())
            navigateToLoginScreen()
        }
        if (logoutResult is Resource.Error) {
            Toast.makeText(context, logoutResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    InfoDialog(
        state = logoutDialog,
        selection = InfoSelection(onPositiveClick = accountViewModel::logout,),
        body = InfoBody.Default(
            bodyText = "Apakah yakin ingin keluar dari aplikasi?"
        )
    )

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Profilku", onNavigationClicked = onNavigationClicked)
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
        ) {
            Column(modifier = Modifier) {
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
                            jajanName,
                            vendorName,
                            vendorUsername,
                            vendorLevel,
                            imageUrl
                        )
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Black, CircleShape)
                    ) {
                        IconButton(
                            onClick = navigateToEditAccountScreen,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Profile",
                                tint = Color.White
                            )
                        }
                    }
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
                            menuTitle = "Kelola Toko",
                            menuDescription = "Atur toko saya",
                            showEnter = true,
                            onClick = navigateToManageShopScreen
                        )

                        MenuButton(
                            imageVector = Icons.Outlined.StarOutline,
                            menuTitle = "Status Pelanggan",
                            menuDescription = vendorLevel,
                            showEnter = true,
                            onClick = navigateToLevelScreen
                        )

                        Divider(thickness = 8.dp)

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_event_note),
                            menuTitle = "Ketentuan dan Privasi",
                            menuDescription = "",
                            showEnter = true,
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
fun PreviewAccountScreen() {
    JajanManiaTheme {
        Surface {
            AccountScreen()
        }
    }
}