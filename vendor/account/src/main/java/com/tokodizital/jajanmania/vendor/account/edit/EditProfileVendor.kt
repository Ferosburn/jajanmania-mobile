package com.tokodizital.jajanmania.vendor.account.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.cards.CircleImageCard
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EditProfileVendor(
    modifier: Modifier = Modifier
) {

    var vendorOwner by remember { mutableStateOf("") }
    var vendorName by remember { mutableStateOf("") }
    var vendorPhone by remember { mutableStateOf("") }
    var vendorEmail by remember { mutableStateOf("") }
    var vendorAddress by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Edit Profil") }) },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(90.dp)) {
                    CircleImageCard(
                        dpSize = 90,
                        painter = painterResource(id = R.drawable.placeholder)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(0x50000000), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            modifier = Modifier.fillMaxSize(),
                            onClick = {

                            }
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_photo_camera),
                                ""
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    BaseOutlinedTextField(
                        value = vendorOwner,
                        onValueChanged = { vendorOwner = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nama Pemilik",
                        placeholder = "Nama pemilik",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = vendorName,
                        onValueChanged = { vendorName = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nama Toko/Warung",
                        placeholder = "Nama Toko/Warung",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = vendorPhone,
                        onValueChanged = { vendorPhone = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "No. Telepon",
                        placeholder = "No. Telepon",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = vendorEmail,
                        onValueChanged = { vendorEmail = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Email",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = vendorAddress,
                        onValueChanged = { vendorAddress = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Alamat",
                        placeholder = "Alamat",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseButton(
                        text = "Simpan",
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClicked = {

                        }
                    )

                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewEditProfileScreen() {
    JajanManiaTheme {
        Surface {
            EditProfileVendor()
        }
    }
}