package com.tokodizital.jajanmania.customer.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.HomeTopAppBar
import com.tokodizital.jajanmania.ui.components.cards.CircleImageCard
import com.tokodizital.jajanmania.ui.components.textfields.OutlinedTextFieldWithLabel
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { HomeTopAppBar() },
        modifier = modifier
    ) { paddingValues ->

        val genderList = listOf("Pilih Jenis Kelamin", "Laki - laki", "Perempuan")
        var selectedGender = genderList[0]

        var profileName = rememberSaveable { mutableStateOf("Elon Musk") }
        var profilePhone = rememberSaveable { mutableStateOf("") }
        var profileEmail = rememberSaveable { mutableStateOf("") }
        var profileAddress = rememberSaveable { mutableStateOf("") }
        var profileGender = rememberSaveable { mutableStateOf(selectedGender) }

        var expanded = rememberSaveable { mutableStateOf(false) }

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

                    OutlinedTextFieldWithLabel(
                        value = profileName.value,
                        onValueChange = {profileName.value = it},
                        label = "Nama",
                        inputType = "basic",
                        leadingIcon = Icons.Outlined.AccountCircle,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextFieldWithLabel(
                        value = profilePhone.value,
                        onValueChange = {profilePhone.value = it},
                        label = "No. Telepon",
                        inputType = "basic",
                        leadingIcon = Icons.Outlined.Call,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextFieldWithLabel(
                        value = profileEmail.value,
                        onValueChange = {profileEmail.value = it},
                        label = "Email",
                        inputType = "basic",
                        leadingIcon = Icons.Outlined.Email,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextFieldWithLabel(
                        value = profileAddress.value,
                        onValueChange = {profileAddress.value = it},
                        label = "Alamat",
                        inputType = "basic",
                        leadingIcon = Icons.Outlined.Home,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Box(modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent, RoundedCornerShape(4.dp))
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(2.dp))
                        .clickable {
                            expanded.value = true
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = profileGender.value)
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = {expanded.value = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            genderList.forEach {
                                if (!(it == genderList[0])) {
                                    DropdownMenuItem(
                                        text = { Text(text = it) },
                                        onClick = {
                                            profileGender.value = it
                                            selectedGender = it
                                            expanded.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        onClick = {

                        }) {
                        Text(text = "Simpan")
                    }
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