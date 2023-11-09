package com.tokodizital.jajanmania.customer.profile

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseExtendedFloatingActionButton
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.components.cards.ProfileInfoCard
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
    navigateToEditProfileScreen: () -> Unit = {},
    navigateToTransactionHistoryScreen: () -> Unit = {},
    navigateToMySubscriptionScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {}
) {
    Scaffold(
        topBar = { DetailTopAppBar(title = "Profile Customer", onNavigationClicked = onNavigationClick) },
        modifier = modifier
    ) { paddingValues ->

        var profileName by remember { mutableStateOf("Kaedehara Kazuha") }
        var profileUsername by remember { mutableStateOf("@mapleave") }
        var profileEmail by remember { mutableStateOf("mapleave@thecruxcrew.com") }
        var profileLevel by remember { mutableStateOf("Level: 3") }

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
                            menuDescription = "Level 2",
                            showEnter = true
                        ) {

                        }

                        Divider(thickness = 8.dp)

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_event_note),
                            menuTitle = "Ketentuan dan Privasi",
                            menuDescription = "",
                            showEnter = true
                        ) {

                        }
                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_help),
                            menuTitle = "Pusat Bantuan",
                            menuDescription = "",
                            showEnter = true
                        ) {

                        }
                        MenuButton(
                            imageVector = Icons.Rounded.ExitToApp,
                            menuTitle = "Keluar",
                            menuDescription = "",
                            showEnter = false,
                            onClick = navigateToLoginScreen
                        )
                    }
                }
            }


        }


//        Box(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//                .padding(all = 32.dp),
//            contentAlignment = Alignment.Center,
//        ) {
//            Text(text = "Welcome to profile screen customer!")
//        }
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