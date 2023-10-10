package com.tokodizital.jajanmania.customer.profile

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.cards.CircleImageCard
import com.tokodizital.jajanmania.ui.components.textfields.BaseAutoCompleteTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Edit Profile Customer") }) },
        modifier = modifier
    ) { paddingValues ->

        val focusManager = LocalFocusManager.current
        val genderList = remember {
            listOf(
                "Laki - laki",
                "Perempuan"
            )
        }

        var profileName by remember { mutableStateOf("") }
        var profilePhone by remember { mutableStateOf("") }
        var profileEmail by remember { mutableStateOf("") }
        var profileAddress by remember { mutableStateOf("") }
        var profileGender by remember { mutableStateOf("") }

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
                        value = profileName,
                        onValueChanged = { profileName = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nama",
                        placeholder = "Masukan nama",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = profilePhone,
                        onValueChanged = { profilePhone = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "No. Telepon",
                        placeholder = "Masukan nomor telepon",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = profileEmail,
                        onValueChanged = { profileEmail = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Masukan email",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = profileAddress,
                        onValueChanged = { profileAddress = it},
                        modifier = Modifier.fillMaxWidth(),
                        label = "Alamat",
                        placeholder = "Masukan alamat",
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )

                    BaseAutoCompleteTextField(
                        value = profileGender,
                        label = "Jenis Kelamin",
                        placeholder = "Pilih Jenis Kelamin",
                        modifier = Modifier.fillMaxWidth(),
                        items = genderList,
                        onItemClicked = {
                            profileGender = it
                            focusManager.clearFocus()
                        }
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
            EditProfileScreen()
        }
    }
}