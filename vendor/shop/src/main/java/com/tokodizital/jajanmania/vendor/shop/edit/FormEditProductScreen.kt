package com.tokodizital.jajanmania.vendor.shop.edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseAutoCompleteTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.shop.component.ImageProductSection

@ExperimentalMaterial3Api
@Composable
fun FormEditProductScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigationToShopScreen: () -> Unit = {},
    jajanId: String = ""
) {

    val focusManager = LocalFocusManager.current
    var image = Uri.EMPTY

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        image = uri
    }

    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }

    val launchGallery: () -> Unit = {
        imageLauncher.launch("image/*")
    }

    val productCategoryItems = remember {
        listOf(
            "Bakso",
            "Cilok",
            "Seblak",
            "Sempol"
        )
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Edit Produk",
                onNavigationClicked = onNavigationClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                ImageProductSection(
                    image = image,
                    openGallery = launchGallery,
                    modifier = Modifier
                        .padding(horizontal = 56.dp, vertical = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
                BaseOutlinedTextField(
                    value = productName,
                    onValueChanged = { productName = it },
                    label = "Nama Produk",
                    placeholder = "Masukan nama produk",
                    singleLine = true,
                    type = BaseOutlinedTextFieldType.WithClearIcon,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                BaseOutlinedTextField(
                    value = productPrice,
                    onValueChanged = { productPrice = it },
                    label = "Harga",
                    placeholder = "Masukan harga produk",
                    singleLine = true,
                    type = BaseOutlinedTextFieldType.WithClearIcon,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                BaseAutoCompleteTextField(
                    value = productCategory,
                    label = "Kategori",
                    placeholder = "Masukan kategori produk",
                    onItemClicked = {
                        productCategory = it
                        focusManager.clearFocus(force = true)
                    },
                    items = productCategoryItems,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            BaseButton(
                text = stringResource(R.string.label_save),
                onClicked = navigationToShopScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            BaseButton(
                text = stringResource(R.string.label_delete),
                onClicked = navigationToShopScreen,
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewFormEditProductScreen() {
    JajanManiaTheme {
        Surface {
            FormEditProductScreen()
        }
    }
}
