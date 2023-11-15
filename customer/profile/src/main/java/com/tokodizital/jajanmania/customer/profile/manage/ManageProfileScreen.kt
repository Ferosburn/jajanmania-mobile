package com.tokodizital.jajanmania.customer.profile.manage

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.cards.CircleImageCard
import com.tokodizital.jajanmania.ui.components.textfields.BaseAutoCompleteTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
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
    val fullName = manageProfileUiState.fullName
    val email = manageProfileUiState.email
    val address = manageProfileUiState.address
    val gender = manageProfileUiState.gender

    val genderList = remember {
        listOf(
            "Laki - laki",
            "Perempuan"
        )
    }

//    var profileName by remember { mutableStateOf("") }
//    var profileEmail by remember { mutableStateOf("") }
//    var profileAddress by remember { mutableStateOf("") }
//    var profileGender by remember { mutableStateOf("") }

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

    LaunchedEffect(key1 = customer) {
        if (customer is Resource.Success) {
            onFullNameChange(customer.data.fullName)
            onEmailChange(customer.data.email)
            onAddressChange(customer.data.address)
            onGenderChange(when (customer.data.gender) {
                "MALE" -> genderList[0]
                else -> genderList[1]
            })
        }
    }


    Scaffold(
        topBar = { DetailTopAppBar(title = "Edit Profile Customer", onNavigationClicked = onNavigationClick) },
        modifier = modifier
    ) { paddingValues ->

        val focusManager = LocalFocusManager.current

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
                        value = fullName,
                        onValueChanged = onFullNameChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nama",
                        placeholder = "Masukan nama",
                        type = BaseOutlinedTextFieldType.WithClearIcon,
                        errorText = manageProfileUiState.errorFullNameMessage
                    )
                    BaseOutlinedTextField(
                        value = email,
                        onValueChanged = onEmailChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Masukan email",
                        enabled = false,
                        type = BaseOutlinedTextFieldType.WithClearIcon
                    )
                    BaseOutlinedTextField(
                        value = address,
                        onValueChanged = onAddressChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Alamat",
                        placeholder = "Masukan alamat",
                        type = BaseOutlinedTextFieldType.WithClearIcon,
                        errorText = manageProfileUiState.errorAddressMessage
                    )

                    BaseAutoCompleteTextField(
                        value = gender,
                        label = "Jenis Kelamin",
                        placeholder = "Pilih Jenis Kelamin",
                        modifier = Modifier.fillMaxWidth(),
                        items = genderList,
                        onItemClicked = onGenderChange
                    )

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
            ManageProfileScreen()
        }
    }
}