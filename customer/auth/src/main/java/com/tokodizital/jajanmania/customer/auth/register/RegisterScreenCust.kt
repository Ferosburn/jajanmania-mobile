package com.tokodizital.jajanmania.customer.auth.register

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
import com.tokodizital.jajanmania.common.data.Gender
import com.tokodizital.jajanmania.common.utils.isValidEmail
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseTextButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.components.textfields.BasePasswordOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.OutlineDropdownMenuBox
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.theme.poppins
import com.tokodizital.jajanmania.ui.uicontroller.StatusBarColor
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun RegisterScreenCust(
    modifier: Modifier = Modifier,
    registerCustViewModel: RegisterCustViewModel = koinViewModel(),
    navigateToLoginScreen: () -> Unit = {}
) {

    val context = LocalContext.current

    val registerCustUiState by registerCustViewModel.registerCustUiState.collectAsState()
    val fullName = registerCustUiState.fullName
    val username = registerCustUiState.username
    val email = registerCustUiState.email
    val gender = registerCustUiState.gender
    val password = registerCustUiState.password
    val confirmPassword = registerCustUiState.confirmPassword
    val registerResult = registerCustUiState.registerResult
    val buttonRegisterEnabled by registerCustViewModel.buttonRegisterEnabled.collectAsState(initial = false)
    val buttonRegisterLoading by registerCustViewModel.buttonRegisterLoading.collectAsState(initial = false)

    val options = Gender.values()

    LaunchedEffect(key1 = registerResult) {
        if (registerResult is Resource.Success) {
            val message = registerResult.data.message
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            navigateToLoginScreen()
        }
        if (registerResult is Resource.Error) {
            val message = registerResult.message
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    val onFullNameChange: (String) -> Unit = { text ->
        val errorMessage = if (text.isNotEmpty()) R.string.empty else R.string.message_name_required
        registerCustViewModel.updateFullName(text)
        registerCustViewModel.updateErrorFullNameMessage(context.getString(errorMessage))
    }

    val onUserNameChange: (String) -> Unit = { text ->
        val errorMessage =
            if (text.isNotEmpty()) R.string.empty else R.string.message_username_required
        registerCustViewModel.updateUsername(text)
        registerCustViewModel.updateErrorUsernameMessage(context.getString(errorMessage))
    }

    val onEmailChange: (String) -> Unit = { text ->
        val errorMessage =
            if (text.isValidEmail()) R.string.empty else R.string.message_email_bad_format
        registerCustViewModel.updateEmail(text)
        registerCustViewModel.updateErrorEmailMessage(context.getString(errorMessage))
    }

    val onGenderChange: (String) -> Unit = { text ->
        registerCustViewModel.updateGender(text)
    }

    val onPasswordChange: (String) -> Unit = { text ->
        val errorMessage =
            if (text.length >= 6) R.string.empty else R.string.message_password_less_than_six
        registerCustViewModel.updatePassword(text)
        registerCustViewModel.updateErrorPasswordMessage(context.getString(errorMessage))
    }

    val onConfirmPasswordChange: (String) -> Unit = { text ->
        val errorMessage =
            if (text == password) R.string.empty else R.string.message_confirm_password_not_match
        registerCustViewModel.updateConfirmPassword(text)
        registerCustViewModel.updateErrorConfirmPasswordMessage(context.getString(errorMessage))
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
                .padding(horizontal = 26.dp)
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
                text = "Daftar Pengguna",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseOutlinedTextField(
                value = fullName,
                onValueChanged = onFullNameChange,
                label = "Nama Lengkap",
                placeholder = "Masukkan nama lengkap",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = registerCustUiState.errorFullNameMessage,
            )
            Spacer(modifier = Modifier.height(4.dp))
            BaseOutlinedTextField(
                value = username,
                onValueChanged = onUserNameChange,
                label = "Username",
                placeholder = "Masukkan username",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = registerCustUiState.errorUsernameMessage,
            )
            Spacer(modifier = Modifier.height(4.dp))
            BaseOutlinedTextField(
                value = email,
                onValueChanged = onEmailChange,
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
                errorText = registerCustUiState.errorEmailMessage,
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlineDropdownMenuBox(
                modifier = Modifier
                    .fillMaxWidth(),
                options = options.map { it.displayName },
                label = "Jenis Kelamin",
                placeholder = "Pilih jenis kelamin",
                selectedOption = gender,
                onOptionSelected = onGenderChange,
                errorText = registerCustUiState.errorGenderMessage
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasePasswordOutlinedTextField(
                value = password,
                onValueChanged = onPasswordChange,
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
                errorText = registerCustUiState.errorPasswordMessage
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasePasswordOutlinedTextField(
                value = confirmPassword,
                onValueChanged = onConfirmPasswordChange,
                label = "Konfirmasi Kata Sandi",
                placeholder = "Masukkan kembali kata sandi",
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = registerCustUiState.errorConfirmPasswordMessage
            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseButton(
                text = "Daftar",
                onClicked = registerCustViewModel::register,
                enabled = buttonRegisterEnabled,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                isLoading = buttonRegisterLoading
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.offset(y = 2.5.dp),
                    underline = true
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RegisterScreenPreview() {
    JajanManiaTheme {
        Surface {
            RegisterScreenCust()
        }
    }
}