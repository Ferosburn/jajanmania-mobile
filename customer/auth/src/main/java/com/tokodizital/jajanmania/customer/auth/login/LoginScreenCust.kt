package com.tokodizital.jajanmania.customer.auth.login

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseTextButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.components.textfields.BasePasswordOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.poppins
import com.tokodizital.jajanmania.ui.uicontroller.StatusBarColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenCust(
    modifier: Modifier = Modifier,
    loginCustViewModel: LoginCustViewModel = koinViewModel(),
    navigateToHomeScreen: () -> Unit = {},
    navigateToRegisterScreen: () -> Unit = {}
) {

    val context = LocalContext.current

    val loginCustUiState by loginCustViewModel.loginCustUiState.collectAsState()
    val email = loginCustUiState.email
    val password = loginCustUiState.password
    val loginResult = loginCustUiState.loginResult

    val buttonLoginEnabled by loginCustViewModel.buttonLoginEnabled.collectAsState(initial = false)
    val buttonLoginLoading by loginCustViewModel.buttonLoginLoading.collectAsState(initial = false)

    LaunchedEffect(key1 = loginResult) {
        if (loginResult is Resource.Success) {
            loginCustViewModel.updateCustomerSession(loginResult.data)
            navigateToHomeScreen()
        }
        if (loginResult is Resource.Error) {
            Toast.makeText(context, loginResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    StatusBarColor(
        color = MaterialTheme.colorScheme.background
    )

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_jajan_mania),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Masuk Pengguna",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseOutlinedTextField(
                value = email,
                onValueChanged = loginCustViewModel::updateEmail,
                label = "Email",
                placeholder = "Masukkan email",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = loginCustUiState.errorEmailMessage
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasePasswordOutlinedTextField(
                value = password,
                onValueChanged = loginCustViewModel::updatePassword,
                label = "Kata Sandi",
                placeholder = "Masukkan kata sandi",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = loginCustUiState.errorPasswordMessage

            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseButton(
                text = "Login",
                onClicked = loginCustViewModel::login,
                containerColor = MaterialTheme.colorScheme.primary,
                enabled = buttonLoginEnabled,
                isLoading = buttonLoginLoading,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Belum punya akun?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                BaseTextButton(
                    underline = true,
                    text = "Daftar di sini",
                    fontSize = 12.sp,
                    onClicked = navigateToRegisterScreen,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.offset(y = 2.5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    JajanManiaTheme {
        Surface {
            LoginScreenCust()
        }
    }
}