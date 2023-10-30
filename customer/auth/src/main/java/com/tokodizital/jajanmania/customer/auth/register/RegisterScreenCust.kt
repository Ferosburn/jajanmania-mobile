package com.tokodizital.jajanmania.customer.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseTextButton
import com.tokodizital.jajanmania.ui.components.dropdown.BaseDropdown
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.components.textfields.BasePasswordOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.poppins
import com.tokodizital.jajanmania.ui.uicontroller.StatusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenCust(
    modifier: Modifier = Modifier,
    registerCustViewModel: RegisterCustViewModel = viewModel(),
    navigateToLoginScreen: () -> Unit = {}
) {
    val registerCustUiState by registerCustViewModel.registerCustUiState.collectAsState()

    val fullname = registerCustUiState.fullname
    val email = registerCustUiState.email
    val username = registerCustUiState.username
    val password = registerCustUiState.password
    val confirmPassword = registerCustUiState.confirmPassword

    val buttonRegisterEnabled by registerCustViewModel.buttonRegisterEnabled.collectAsState(initial = false)

    StatusBarColor(
        color = MaterialTheme.colorScheme.background
    )

    Scaffold (
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 26.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_jajan_mania),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Daftar Pengguna",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = poppins
            )

            Spacer(modifier = Modifier.height(25.dp))

            BaseOutlinedTextField(
                value = fullname,
                onValueChanged = registerCustViewModel::updateFullname,
                label = "Nama Lengkap",
                placeholder = "Nama Lengkap",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(20.dp))

            BaseOutlinedTextField(
                value = username,
                onValueChanged = registerCustViewModel::updateUsername,
                label = "Username",
                placeholder = "Username",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(20.dp))

            BaseOutlinedTextField(
                value = email,
                onValueChanged = registerCustViewModel::updateEmail,
                label = "Email",
                placeholder = "Email",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(20.dp))

            BaseDropdown(
                items = arrayOf("Pilih jenis kelamin", "Pria", "Wanita"),
                onItemSelected = { selectedGender ->
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            BasePasswordOutlinedTextField(
                value = password,
                onValueChanged = registerCustViewModel::updatePassword,
                label = "Kata Sandi",
                placeholder = "Kata Sandi",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(20.dp))

            BasePasswordOutlinedTextField(
                value = confirmPassword,
                onValueChanged = registerCustViewModel::updateConfirmPassword,
                label = "Konfirmasi Kata Sandi",
                placeholder = "Konfirmasi Kata Sandi",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(30.dp))

            BaseButton(
                text = "Daftar",
                onClicked = navigateToLoginScreen,
                enabled = buttonRegisterEnabled,
                containerColor = Color(0xFF343434),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Sudah punya akun?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                BaseTextButton(
                    text = "Masuk di sini",
                    fontSize = 12.sp,
                    onClicked = navigateToLoginScreen,
                    contentColor = Color(0XFF000000),
                    modifier = Modifier.offset(y = 2.5.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RegisterScreenPerview() {
    JajanManiaTheme() {
        Surface {
            RegisterScreenCust()
        }
    }
}