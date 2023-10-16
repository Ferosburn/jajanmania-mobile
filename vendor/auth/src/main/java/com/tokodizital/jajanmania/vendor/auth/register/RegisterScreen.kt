package com.tokodizital.jajanmania.vendor.auth.register

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
import com.tokodizital.jajanmania.ui.components.checkbox.BaseCheckBox
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.components.textfields.BasePasswordOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.poppins

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel()
) {

    val registerUiState by registerViewModel.registerUiState.collectAsState()
    val shopName = registerUiState.shopName
    val ownerName = registerUiState.ownerName
    val email = registerUiState.email
    val password = registerUiState.password
    val confirmPassword = registerUiState.confirmPassword
    val userAgreement = registerUiState.userAgreement

    val buttonRegisterEnabled by registerViewModel.buttonRegisterEnabled.collectAsState(initial = false)

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 26.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_jajan_mania),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(133.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Register Vendor",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(42.dp))
            BaseOutlinedTextField(
                value = shopName,
                onValueChanged = registerViewModel::updateShopName,
                label = "Nama Toko/Warung",
                placeholder = "Masukan nama toko/warung",
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
                value = ownerName,
                onValueChanged = registerViewModel::updateOwnerName,
                label = "Nama Lengkap Pemilik",
                placeholder = "Masukan nama lengkap pemiliki",
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
                onValueChanged = registerViewModel::updateEmail,
                label = "Email",
                placeholder = "Masukan email",
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
            BasePasswordOutlinedTextField(
                value = password,
                onValueChanged = registerViewModel::updatePassword,
                label = "Password",
                placeholder = "Masukan password",
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
                onValueChanged = registerViewModel::updateConfirmPassword,
                label = "Konfirmasi Password",
                placeholder = "Masukan password kembali",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaseCheckBox(
                text = "I agree to Terms and Conditions of Jajan Mania",
                checked = userAgreement,
                onCheckedChanged = registerViewModel::updateUserAgreement
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaseButton(
                text = "Register",
                onClicked = {},
                enabled = buttonRegisterEnabled,
                containerColor = Color(0xFF343434),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                BaseTextButton(
                    text = "Click Here",
                    fontSize = 12.sp,
                    onClicked = {},
                    contentColor = Color(0XFF17C05B),
                    modifier = Modifier.offset(y = 2.5.dp)
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewRegisterScreen() {
    JajanManiaTheme {
        Surface {
            RegisterScreen()
        }
    }
}
