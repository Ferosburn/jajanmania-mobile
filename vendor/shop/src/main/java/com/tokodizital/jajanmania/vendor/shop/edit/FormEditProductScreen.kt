package com.tokodizital.jajanmania.vendor.shop.edit

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseAutoCompleteTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.vendor.shop.component.ImageProductSection
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun FormEditProductScreen(
    modifier: Modifier = Modifier,
    onNavigationClicked: () -> Unit = {},
    navigationToShopScreen: () -> Unit = {},
    jajanId: String = "",
    formUpdateProductViewModel: FormUpdateProductViewModel = koinViewModel()
) {

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val productUiState by formUpdateProductViewModel.fromUpdateUiState.collectAsState()
    val image = productUiState.imageUri
    val productName = productUiState.productName
    val productPrice = productUiState.productPrice
    val productUrl = productUiState.productUrl
    val productCategory = productUiState.productCategory
    val vendorSession = productUiState.vendorSession
    val postImageResult = productUiState.postImageResult
    val updateJajanResult = productUiState.addJajanResult
    val getJajanResult = productUiState.getJajanResult
    val categoriesList = productUiState.categoriesList
    val deleteResult = productUiState.deleteJajanResult
    val buttonSaveLoading by formUpdateProductViewModel.buttonSaveLoading.collectAsState(initial = false)
    val buttonSaveEnabled by formUpdateProductViewModel.buttonSaveEnabled.collectAsState(initial = false)

    val onProductNameChanged: (String) -> Unit = { text ->
        val errorMessage = if (text.isNotEmpty()) R.string.empty else R.string.message_name_required
        formUpdateProductViewModel.updateName(text)
        formUpdateProductViewModel.updateNameErrorMessage(context.getString(errorMessage))
    }

    val onUriChanged: (Uri) -> Unit = { uri ->
        formUpdateProductViewModel.updateImageUri(uri)
    }

    val onProductPriceChanged: (String) -> Unit = { text ->
        val parsedPrice = text.toIntOrNull() ?: 0
        val errorMessage = if (parsedPrice > 0) R.string.empty else R.string.message_price_less_than_zero
        formUpdateProductViewModel.updatePrice(parsedPrice)
        formUpdateProductViewModel.updatePriceErrorMessage(context.getString(errorMessage))
    }

    val onProductCategoryChanged: (String) -> Unit = { text ->
        val errorMessage = if (text != "Kategori") R.string.empty else R.string.message_choose_category
        formUpdateProductViewModel.updateCategory(text)
        formUpdateProductViewModel.updateCategoryErrorMessage(context.getString(errorMessage))
    }


    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        onUriChanged(uri ?: Uri.EMPTY)
    }

    val launchGallery: () -> Unit = {
        imageLauncher.launch("image/*")
    }

    LaunchedEffect(key1 = Unit) {
        formUpdateProductViewModel.getVendorSession()
    }

    LaunchedEffect(key1 = vendorSession) {
        if (vendorSession is Resource.Success) {
            val session = vendorSession.data
            formUpdateProductViewModel.updateToken(
                token = session.accessToken,
            )
            formUpdateProductViewModel.updateId(
                id = session.accountId,
            )
            formUpdateProductViewModel.getCategories(
                token = session.accessToken,
            )
            delay(1000L)
            formUpdateProductViewModel.getJajanById(
                token = session.accessToken,
                jajanId = jajanId
            )
        }
    }

    LaunchedEffect(key1 = getJajanResult) {
        if (getJajanResult  is Resource.Error) {
            Toast.makeText(context, getJajanResult.message, Toast.LENGTH_SHORT).show()
            onNavigationClicked()
        }
    }

    LaunchedEffect(key1 = getJajanResult) {
        if (getJajanResult  is Resource.Error) {
            Toast.makeText(context, getJajanResult.message, Toast.LENGTH_SHORT).show()
            onNavigationClicked()
        }
    }

    LaunchedEffect(key1 = postImageResult) {
        if (postImageResult is Resource.Success) {
            formUpdateProductViewModel.updateImageUrl(postImageResult.data.url)
            formUpdateProductViewModel.updateJajan(jajanId)
        } else if (postImageResult is Resource.Error) {
            Toast.makeText(context, postImageResult.message, Toast.LENGTH_SHORT).show()
        }
    }


    LaunchedEffect(key1 = updateJajanResult) {
        if (updateJajanResult is Resource.Success) {
            navigationToShopScreen()
        } else if (updateJajanResult is Resource.Error) {
            Toast.makeText(context, updateJajanResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = deleteResult) {
        if (deleteResult is Resource.Success) {
            navigationToShopScreen()
        } else if (deleteResult is Resource.Error) {
            Toast.makeText(context, deleteResult.message, Toast.LENGTH_SHORT).show()
        }
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
                    onValueChanged = onProductNameChanged,
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
                    value = productPrice.toString(),
                    onValueChanged = onProductPriceChanged,
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
                        onProductCategoryChanged(it)
                        focusManager.clearFocus(force = true)
                    },
                    items = categoriesList.map { it.name },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            BaseButton(
                text = stringResource(R.string.label_save),
                onClicked = {
                    if (image != Uri.EMPTY)
                        formUpdateProductViewModel.postPicture(context)
                    else
                        formUpdateProductViewModel.updateJajan(jajanId)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            BaseButton(
                text = stringResource(R.string.label_delete),
                onClicked = { formUpdateProductViewModel.deleteJajanById(jajanId) },
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
