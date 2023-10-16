package com.tokodizital.jajanmania.vendor.account.profil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.tokodizital.jajanmania.ui.components.buttons.BaseExtendedFloatingActionButton
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.components.cards.ProfileInfoCard
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier
) {

    var vendorName by remember { mutableStateOf("Batagor Bang Tigor") }
    var vendorOwner by remember { mutableStateOf("Tigor") }
    var vendorPhone by remember { mutableStateOf("+6285261616161") }
    var vendorLevel by remember { mutableStateOf("LEVEL 1") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Profilku") }) },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
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
                            vendorName,
                            vendorOwner,
                            vendorPhone,
                            vendorLevel
                        )
                    }
                    BaseExtendedFloatingActionButton(
                        modifier = Modifier.size(50.dp),
                        icon = R.drawable.ic_edit,
                        text = "",
                        onClick = {

                        }
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