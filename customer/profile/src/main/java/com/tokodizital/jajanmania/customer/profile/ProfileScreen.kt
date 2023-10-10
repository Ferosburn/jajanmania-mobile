package com.tokodizital.jajanmania.customer.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.components.cards.ProfileInfoCard
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { HomeTopAppBar() },
        modifier = modifier
    ) { paddingValues ->

        var profileName = rememberSaveable { mutableStateOf("Elon Musk") }
        var profilePhone = rememberSaveable { mutableStateOf("+62 852 6161 6161") }
        var profileEmail = rememberSaveable { mutableStateOf("ElonMusk@twitter.com") }
        var profileLevel = rememberSaveable { mutableStateOf("Level 1") }


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
                            profileName.value,
                            profilePhone.value,
                            profileEmail.value,
                            profileLevel.value
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = Color.Blue,
                                shape = CircleShape
                            )
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
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
                            showEnter = true
                        ) {

                        }

                        MenuButton(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_store),
                            menuTitle = "Langgananku",
                            menuDescription = "Atur vendor langgananku",
                            showEnter = true
                        ) {

                        }

                        MenuButton(
                            imageVector = Icons.Outlined.Notifications,
                            menuTitle = "Notifikasi",
                            menuDescription = "Atur notifikasi",
                            showEnter = true
                        ) {

                        }

                        Divider(thickness = 8.dp)

                        MenuButton(
                            imageVector = Icons.Outlined.Settings,
                            menuTitle = "Pengaturan Aplikasi",
                            menuDescription = "",
                            showEnter = true
                        ) {

                        }

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
                            showEnter = false
                        ) {

                        }
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