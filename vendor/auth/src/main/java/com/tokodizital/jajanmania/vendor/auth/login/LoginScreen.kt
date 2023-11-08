package com.tokodizital.jajanmania.vendor.auth.login

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.common.utils.isValidEmail
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

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel(),
    navigateToRegisterScreen: () -> Unit = {},
    navigateToDashboardScreen: () -> Unit = {}
) {

    val context = LocalContext.current

    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val email = loginUiState.email
    val password = loginUiState.password
    val loginResult = loginUiState.loginResult
    val buttonLoginEnabled by loginViewModel.buttonLoginEnabled.collectAsState(initial = false)
    val buttonLoginLoading by loginViewModel.buttonLoginLoading.collectAsState(initial = false)

    LaunchedEffect(key1 = loginResult) {
        if (loginResult is Resource.Success) {
            loginViewModel.updateVendorSession(
                loginResult.data
            )
            navigateToDashboardScreen()
        }
        if (loginResult is Resource.Error) {
            Toast.makeText(context, loginResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    val onEmailChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.isValidEmail()) R.string.empty else R.string.message_email_bad_format
        loginViewModel.updateEmail(text)
        loginViewModel.updateEmailErrorMessage(context.getString(errorMessage))
    }

    val onPasswordChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.length >= 6) R.string.empty else R.string.message_password_less_than_six
        loginViewModel.updatePassword(text)
        loginViewModel.updatePasswordErrorMessage(context.getString(errorMessage))
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
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(148.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_jajan_mania),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Masuk Pedagang",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseOutlinedTextField(
                value = email,
                onValueChanged = onEmailChanged,
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
                errorText = loginUiState.errorEmailMessage,
            )
            Spacer(modifier = Modifier.height(4.dp))
            BasePasswordOutlinedTextField(
                value = password,
                onValueChanged = onPasswordChanged,
                label = "Password",
                placeholder = "Masukan password",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = loginUiState.errorPasswordMessage
            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseButton(
                text = "Login",
                onClicked = loginViewModel::login,
                containerColor = Color(0xFF343434),
                enabled = buttonLoginEnabled,
                isLoading = buttonLoginLoading,
                modifier = Modifier.align(Alignment.CenterHorizontally),
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
                    text = "Daftar Disini",
                    fontSize = 12.sp,
                    onClicked = navigateToRegisterScreen,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.offset(y = 2.5.dp)
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoginScreen() {
    JajanManiaTheme {
        Surface {
            LoginScreen()
        }
    }
}
