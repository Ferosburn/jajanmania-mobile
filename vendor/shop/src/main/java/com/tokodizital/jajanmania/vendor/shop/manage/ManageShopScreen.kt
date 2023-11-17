package com.tokodizital.jajanmania.vendor.shop.manage

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputTextField
import com.maxkeppeler.sheets.input.models.ValidationResult
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.MenuButton
import com.tokodizital.jajanmania.ui.components.buttons.MenuButtonSwitch
import com.tokodizital.jajanmania.ui.components.dialog.BaseInputDialog
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ManageShopScreen(
    modifier: Modifier = Modifier,
    manageShopViewModel: ManageShopViewModel = koinViewModel(),
    onNavigationClicked: () -> Unit = {},
    navigateManageProduct: () -> Unit = {},
) {

    val context = LocalContext.current

    val manageShopUiState by manageShopViewModel.manageShopUiState.collectAsStateWithLifecycle()
    val switchManageShopEnable by manageShopViewModel.switchManageShopEnable.collectAsStateWithLifecycle(
        initialValue = false
    )
    val vendorSession = manageShopUiState.vendorSession
    val isShopActive = manageShopUiState.isShopActive

    var isSwitchChecked by remember { mutableStateOf(false) }

    val state = rememberUseCaseState()

    LaunchedEffect(key1 = Unit) {
        manageShopViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            manageShopViewModel.getShopStatus(
                vendorSession.data.accessToken,
                vendorSession.data.accountId
            )
        }
    }

    LaunchedEffect(key1 = isShopActive) {
        if (isShopActive is Resource.Success) {
            isSwitchChecked = isShopActive.data
            manageShopViewModel.updateTempStatusShop(isShopActive.data)
        }
    }

    LaunchedEffect(key1 = isShopActive) {
        if (isShopActive is Resource.Error) {
            Toast.makeText(context, isShopActive.message, Toast.LENGTH_SHORT).show()
        }
    }

//    BaseInputDialog(
//        state = state,
//        validation = { value ->
//            if ((value?.length ?: 0) >= 6) ValidationResult.Valid
//            else ValidationResult.Invalid(context.getString(R.string.message_password_less_than_six))
//        },
//        title = "Masukan password anda!",
//        icon = Icons.Filled.Password,
//        onPositiveClicked = {}
//    )

    BaseInputDialog(
        state = state,
        textField = InputTextField(
            header = InputHeader(
                title = "Masukan password anda!",
                icon = IconSource(Icons.Filled.Password)
            ),
            visualTransformation = PasswordVisualTransformation(),
            changeListener = { value ->
                if (value != null) manageShopViewModel.updatePassword(value)
            },
            validationListener = { value ->
                if ((value?.length ?: 0) >= 6) ValidationResult.Valid
                else ValidationResult.Invalid(context.getString(R.string.message_password_less_than_six))
            },
            singleLine = true
        ),
        onPositiveClicked = manageShopViewModel::updateShopStatus
    )

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Kelola Toko",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            stickyHeader {
                Text(
                    text = "Toko",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 16.dp)
                )
            }

            item {
                MenuButtonSwitch(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_setting),
                    menuTitle = "Aktifkan/Non Aktifkan Penjualan",
                    menuDescription = "Silahkan aktifkan jika kamu sedang berjualan agar pembeli dapat menemukanmu",
                    isChecked = isSwitchChecked,
                    onSwitchChanged = {
                        state.show()
                    },
                    switchEnabled = switchManageShopEnable
                )
            }

            item {
                MenuButton(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_archive),
                    menuTitle = "Kelola Jajanan",
                    menuDescription = "Kelola nama dan harga produk yang dijual",
                    showEnter = true,
                    onClick = navigateManageProduct
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewManageShopScreen() {
    JajanManiaTheme {
        Surface {
            ManageShopScreen()
        }
    }
}

