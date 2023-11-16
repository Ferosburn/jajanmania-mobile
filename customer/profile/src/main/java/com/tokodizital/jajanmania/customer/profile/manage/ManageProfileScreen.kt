package com.tokodizital.jajanmania.customer.profile.manage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.common.data.Gender
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.cards.CircleImageCard
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.components.textfields.BasePasswordOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.OutlineDropdownMenuBox
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun ManageProfileScreen(
    modifier: Modifier = Modifier,
    manageProfileViewModel: ManageProfileViewModel = koinViewModel(),
    onNavigationClick: () -> Unit = {},
    navigateToProfileScreen: () -> Unit = {}
) {
    val context = LocalContext.current

    val manageProfileUiState by manageProfileViewModel.manageProfileUiState.collectAsStateWithLifecycle()
    val customerSession = manageProfileUiState.customerSession
    val customer = manageProfileUiState.customer
    val customerUpdateResult = manageProfileUiState.customerUpdateResult
    val fullName = manageProfileUiState.fullName
    val email = manageProfileUiState.email
    val address = manageProfileUiState.address
    val gender = manageProfileUiState.gender
    val genderCode = manageProfileUiState.genderCode
    val oldPassword = manageProfileUiState.oldPassword
    val newPassword = manageProfileUiState.newPassword
    val newPasswordConfirm = manageProfileUiState.newPasswordConfirm
    val buttonUpdateEnabled by manageProfileViewModel.buttonUpdateEnabled.collectAsState(initial = false)
    val buttonUpdateLoading by manageProfileViewModel.buttonUpdateLoaging.collectAsState(initial = false)

    val genderOptions = Gender.values()

    LaunchedEffect(key1 = customerUpdateResult) {
        if (customerUpdateResult is Resource.Success) {
            Toast.makeText(context, "Berhasil memperbarui.", Toast.LENGTH_SHORT).show()
            navigateToProfileScreen()
        }
        if (customerUpdateResult is Resource.Error) {
            Toast.makeText(context, customerUpdateResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = Unit) {
        manageProfileViewModel.getCustomerSession()
    }

    LaunchedEffect(key1 = customerSession) {
        if (customerSession is Resource.Success) {
            val session = customerSession.data
            manageProfileViewModel.getCustomer(
                token = session.accessToken,
                id = session.accountId
            )
            manageProfileViewModel.saveId(session.accountId)
            manageProfileViewModel.saveToken(session.accessToken)
        }
    }

    val onFullNameChange: (String) -> Unit = { text ->
        val errorMessage = if (text.isNotEmpty()) R.string.empty else R.string.message_name_required
        manageProfileViewModel.updateFullName(text)
        manageProfileViewModel.updateErrorFullNameMessage(context.getString(errorMessage))
    }
    val onEmailChange: (String) -> Unit = { text ->
        manageProfileViewModel.updateEmail(text)
    }
    val onAddressChange: (String) -> Unit = { text ->
        val errorMessage = if (text.isNotEmpty()) R.string.empty else R.string.message_address_required
        manageProfileViewModel.updateAddress(text)
        manageProfileViewModel.updateErrorAddressMessage(context.getString(errorMessage))
    }
    val onGenderChange: (String) -> Unit = { text ->
        manageProfileViewModel.updateGender(text)
    }
    val onOldPasswordChange: (String) -> Unit = { text ->
        val errorMessage = if (text.isEmpty()) {
            context.getString(R.string.message_insert_password)
        } else if (text.length < 6) {
            context.getString(R.string.message_password_less_than_six)
        } else {
            context.getString(R.string.empty)
        }
        manageProfileViewModel.updateOldPassword(text)
        manageProfileViewModel.updateErrorOldPasswordMessage(errorMessage)
    }
    val onNewPasswordChange: (String) -> Unit = { text ->
        manageProfileViewModel.updateNewPassword(text)

        val errorMessage = if (text.isEmpty()) {
            context.getString(R.string.message_insert_password)
        } else if (text.length < 6) {
            context.getString(R.string.message_password_less_than_six)
        } else if (text != newPasswordConfirm) {
            context.getString(R.string.message_confirm_password_not_match)
        } else {
            context.getString(R.string.empty)
        }
        val confirmErrorMessage = if (newPasswordConfirm.isEmpty()) {
            context.getString(R.string.message_insert_password)
        } else if (newPasswordConfirm.length < 6) {
            context.getString(R.string.message_password_less_than_six)
        } else if (newPasswordConfirm != text) {
            context.getString(R.string.message_confirm_password_not_match)
        } else {
            context.getString(R.string.empty)
        }

        manageProfileViewModel.updateErrorNewPasswordMessage(errorMessage)
        manageProfileViewModel.updateErrorNewPasswordConfirmMessage(confirmErrorMessage)

    }
    val onNewPasswordConfirmChange: (String) -> Unit = { text ->
        manageProfileViewModel.updateNewPasswordConfirm(text)

        val confirmErrorMessage = if (text.isEmpty()) {
            context.getString(R.string.message_insert_password)
        } else if (text.length < 6) {
            context.getString(R.string.message_password_less_than_six)
        } else if (text != newPassword) {
            context.getString(R.string.message_confirm_password_not_match)
        } else {
            context.getString(R.string.empty)
        }

        val errorMessage = if (newPassword.isEmpty()) {
            context.getString(R.string.message_insert_password)
        } else if (newPassword.length < 6) {
            context.getString(R.string.message_password_less_than_six)
        } else if (newPassword != text) {
            context.getString(R.string.message_confirm_password_not_match)
        } else {
            context.getString(R.string.empty)
        }

        manageProfileViewModel.updateErrorNewPasswordMessage(errorMessage)
        manageProfileViewModel.updateErrorNewPasswordConfirmMessage(confirmErrorMessage)
    }

    LaunchedEffect(key1 = customer) {
        if (customer is Resource.Success) {
            onFullNameChange(customer.data.fullName)
            onEmailChange(customer.data.email)
            onAddressChange(customer.data.address)
            onGenderChange(when (customer.data.gender) {
                Gender.MALE.name -> Gender.MALE.displayName
                Gender.FEMALE.name -> Gender.FEMALE.displayName
                else -> ""
            })
        }
    }


    Scaffold(
        topBar = { DetailTopAppBar(title = "Edit Profile Customer", onNavigationClicked = onNavigationClick) },
        modifier = modifier
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .imePadding()
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
                        value = fullName,
                        onValueChanged = onFullNameChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nama",
                        placeholder = "Masukan nama",
                        type = BaseOutlinedTextFieldType.WithClearIcon,
                        errorText = manageProfileUiState.errorFullNameMessage
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BaseOutlinedTextField(
                        value = email,
                        onValueChanged = onEmailChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Masukan email",
                        enabled = false,
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BaseOutlinedTextField(
                        value = address,
                        onValueChanged = onAddressChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Alamat",
                        placeholder = "Masukan alamat",
                        type = BaseOutlinedTextFieldType.WithClearIcon,
                        errorText = manageProfileUiState.errorAddressMessage
                    )
                    Spacer(modifier = Modifier.height(4.dp))
//                    BaseAutoCompleteTextField(
//                        value = gender,
//                        label = "Jenis Kelamin",
//                        placeholder = "Pilih Jenis Kelamin",
//                        modifier = Modifier.fillMaxWidth(),
//                        items = genderList,
//                        onItemClicked = onGenderChange
//                    )
                    OutlineDropdownMenuBox(
                        modifier = Modifier.fillMaxWidth(),
                        options = genderOptions.map { it.displayName },
                        label = "Jenis Kelamin",
                        placeholder = "Pilih Jenis Kelamin",
                        selectedOption = gender,
                        onOptionSelected = onGenderChange
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BasePasswordOutlinedTextField(
                        value = oldPassword,
                        onValueChanged = onOldPasswordChange,
                        label = "Kata Sandi Lama",
                        placeholder = "Masukkan kata sandi",
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        errorText = manageProfileUiState.errorOldPasswordMessage
                    )
                    BasePasswordOutlinedTextField(
                        value = newPassword,
                        onValueChanged = onNewPasswordChange,
                        label = "Kata Sandi Baru",
                        placeholder = "Masukkan kata sandi",
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        errorText = manageProfileUiState.errorNewPasswordMessage
                    )
                    BasePasswordOutlinedTextField(
                        value = newPasswordConfirm,
                        onValueChanged = onNewPasswordConfirmChange,
                        label = "Konfirmasi Kata Sandi Baru",
                        placeholder = "Masukkan kata sandi",
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        errorText = manageProfileUiState.errorNewPasswordConfirmMessage
                    )


                    BaseButton(
                        text = "Simpan",
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClicked = {
                            manageProfileViewModel.updateCustomer(
                                fullName = fullName,
                                address = address,
                                gender = genderCode,
                                oldPassword = oldPassword,
                                newPassword = newPassword
                            )
                        },
                        enabled = buttonUpdateEnabled,
                        isLoading = buttonUpdateLoading
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
            ManageProfileScreen()
        }
    }
}