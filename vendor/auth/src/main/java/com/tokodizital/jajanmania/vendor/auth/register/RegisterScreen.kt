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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
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
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = koinViewModel(),
    navigateToLoginScreen: () -> Unit = {}
) {

    val context = LocalContext.current

    val registerUiState by registerViewModel.registerUiState.collectAsState()
    val fillName = registerUiState.fullName
    val userName = registerUiState.userName
    val gender = registerUiState.gender
    val email = registerUiState.email
    val password = registerUiState.password
    val confirmPassword = registerUiState.confirmPassword
    val registerResult = registerUiState.registerResult

    val buttonRegisterEnabled by registerViewModel.buttonRegisterEnabled.collectAsState(initial = false)
    val buttonRegisterLoading by registerViewModel.buttonRegisterLoading.collectAsState(initial = false)

    var registerDialogState by remember { mutableStateOf(false) }
    var registerDialogMessage by remember { mutableStateOf("") }

    val options = listOf("Laki-Laki", "Wanita")
    
    LaunchedEffect(key1 = registerResult) {
        if (registerResult is Resource.Success) {
            registerDialogMessage = registerResult.data.message
            registerDialogState = true
        }
        if (registerResult is Resource.Error) {
            registerDialogMessage = registerResult.data?.message ?: ""
            registerDialogState = true
        }
    }

    val onFullNameChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.isNotEmpty()) R.string.empty else R.string.message_name_required
        registerViewModel.updateFullNameName(text)
        registerViewModel.updateErrorFullNameMessage(context.getString(errorMessage))
    }

    val onUserNameChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.isNotEmpty()) R.string.empty else R.string.message_username_required
        registerViewModel.updateUserName(text)
        registerViewModel.updateErrorUserNameMessage(context.getString(errorMessage))
    }

    val onEmailChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.isValidEmail()) R.string.empty else R.string.message_email_bad_format
        registerViewModel.updateEmail(text)
        registerViewModel.updateErrorEmailMessage(context.getString(errorMessage))
    }

    val onGenderChanged: (String) -> Unit = { text ->
        registerViewModel.updateGender(text)
    }

    val onPasswordChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.length >= 6) R.string.empty else R.string.message_password_less_than_six
        registerViewModel.updatePassword(text)
        registerViewModel.updateErrorPasswordMessage(context.getString(errorMessage))
    }

    val onConfirmPasswordChanged: (String) -> Unit = { text ->
        val errorMessage = if (text == password) R.string.empty else R.string.message_confirm_password_not_match
        registerViewModel.updateConfirmPassword(text)
        registerViewModel.updateErrorConfirmPasswordMessage(context.getString(errorMessage))
    }

    CoreDialog(
        state = rememberUseCaseState(visible = registerDialogState),
        selection = CoreSelection(
            negativeButton = SelectionButton(
                text = "Kembali"
            ),
            onNegativeClick = { registerDialogState = false },
            positiveButton = SelectionButton(
                text = "Lanjut"
            ),
            onPositiveClick = navigateToLoginScreen
        ),
        body = { /*TODO*/ }
    )

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
                text = "Daftar Pedagang",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(32.dp))
            BaseOutlinedTextField(
                value = fillName,
                onValueChanged = onFullNameChanged,
                label = "Nama Lengkap",
                placeholder = "Masukan Nama Lengkap",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = registerUiState.errorFullNameMessage
            )
            Spacer(modifier = Modifier.height(4.dp))
            BaseOutlinedTextField(
                value = userName,
                onValueChanged = onUserNameChanged,
                label = "Username",
                placeholder = "Masukan Username",
                singleLine = true,
                type = BaseOutlinedTextFieldType.WithClearIcon,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                errorText = registerUiState.errorUserNameMessage
            )
            Spacer(modifier = Modifier.height(4.dp))
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
                errorText = registerUiState.errorEmailMessage
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlineDropdownMenuBox(
                modifier = Modifier
                    .fillMaxWidth(),
                options = options,
                label = "Jenis Kelamin",
                placeholder = "Pilih Jenis Kelamin",
                selectedOption = gender,
                onOptionSelected = onGenderChanged
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasePasswordOutlinedTextField(
                value = password,
                onValueChanged = onPasswordChanged,
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
                errorText = registerUiState.errorPasswordMessage
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasePasswordOutlinedTextField(
                value = confirmPassword,
                onValueChanged = onConfirmPasswordChanged,
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
                errorText = registerUiState.errorConfirmPasswordMessage
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaseButton(
                text = "Daftar",
                onClicked = registerViewModel::register,
                enabled = buttonRegisterEnabled,
                containerColor = Color(0xFF343434),
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
                    text = "Masuk Disini",
                    fontSize = 12.sp,
                    onClicked = navigateToLoginScreen,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.offset(y = 2.5.dp),
                    underline = true
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
