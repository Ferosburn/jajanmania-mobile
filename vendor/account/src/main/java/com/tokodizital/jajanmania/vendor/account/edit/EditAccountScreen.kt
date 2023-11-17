package com.tokodizital.jajanmania.vendor.account.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.cards.CircleImageCard
import com.tokodizital.jajanmania.ui.components.textfields.BaseAutoCompleteTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EditAccountScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigateToProfileScreen: () -> Unit = {},
) {

    val focusManager = LocalFocusManager.current
    val genderList = remember {
        listOf(
            "Laki - laki",
            "Perempuan"
        )
    }

    var vendorOwner by remember { mutableStateOf("") }
    var vendorName by remember { mutableStateOf("") }
    var vendorDescription by remember { mutableStateOf("") }
    var vendorEmail by remember { mutableStateOf("") }
    var vendorGender by remember { mutableStateOf("") }
    var vendorAddress by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Edit Profil", onNavigationClicked = onNavigationClicked)
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                    Spacer(modifier = Modifier.height(8.dp))

                    BaseOutlinedTextField(
                        value = vendorEmail,
                        onValueChanged = { vendorEmail = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Email",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    BaseOutlinedTextField(
                        value = vendorName,
                        onValueChanged = { vendorName = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nama Toko/Warung",
                        placeholder = "Nama Toko/Warung",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    BaseOutlinedTextField(
                        value = vendorDescription,
                        onValueChanged = { vendorDescription = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Deskripsi Toko",
                        placeholder = "Deskripsi Toko",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    BaseAutoCompleteTextField(
                        value = vendorGender,
                        label = "Jenis Kelamin",
                        placeholder = "Pilih Jenis Kelamin",
                        modifier = Modifier.fillMaxWidth(),
                        items = genderList,
                        onItemClicked = {
                            vendorGender = it
                            focusManager.clearFocus()
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    BaseOutlinedTextField(
                        value = vendorAddress,
                        onValueChanged = { vendorAddress = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Alamat",
                        placeholder = "Alamat",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    BaseButton(
                        text = "Simpan",
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClicked = navigateToProfileScreen
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
            EditAccountScreen()
        }
    }
}