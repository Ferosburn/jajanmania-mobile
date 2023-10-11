package com.tokodizital.jajanmania.customer.ewallet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun EWalletChangePinScreen(
    modifier: Modifier = Modifier
) {

    var oldPin by remember { mutableStateOf("") }
    var newPin by remember { mutableStateOf("") }
    var confirmNewPin by remember { mutableStateOf("") }
    val setButtonState: (p1: String, p2: String, p3: String) -> Boolean = { p1, p2, p3 ->
        if (p1.isNotEmpty() && p2.isNotEmpty() && p3.isNotEmpty()) {
            p2 == p3
        } else {
            false
        }
    }
    var isButtonEnabled by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Ubah PIN") }) },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
                Column {
                    Text(text = "Ubah PIN")
                    BaseOutlinedTextField(
                        value = oldPin,
                        onValueChanged = {
                            oldPin = it
                            isButtonEnabled = setButtonState(oldPin, newPin, confirmNewPin)

                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = "PIN Lama",
                        placeholder = "Masukkan PIN",
                        keyboardOptions = KeyboardOptions(
                            KeyboardCapitalization.None,
                            false,
                            KeyboardType.NumberPassword,
                            ImeAction.Next
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    BaseOutlinedTextField(
                        value = newPin,
                        onValueChanged = {
                            newPin = it
                            isButtonEnabled = setButtonState(oldPin, newPin, confirmNewPin)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = "PIN Baru",
                        placeholder = "Masukkan PIN",
                        keyboardOptions = KeyboardOptions(
                            KeyboardCapitalization.None,
                            false,
                            KeyboardType.NumberPassword,
                            ImeAction.Next
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    BaseOutlinedTextField(
                        value = confirmNewPin,
                        onValueChanged = {
                            confirmNewPin = it
                            isButtonEnabled = setButtonState(oldPin, newPin, confirmNewPin)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = "Konfirmasi PIN Baru",
                        placeholder = "Masukkan PIN",
                        keyboardOptions = KeyboardOptions(
                            KeyboardCapitalization.None,
                            false,
                            KeyboardType.NumberPassword,
                            ImeAction.Done
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    if (isButtonEnabled) {
                        BaseButton(
                            text = "Lanjut",
                            modifier = Modifier
                                .align(Alignment.End),
                            enabled = isButtonEnabled,
                            onClicked = {
                                showBottomSheet = true
                            }
                        )
                    } else {
                        BaseButton(
                            text = "Lanjut",
                            modifier = Modifier
                                .align(Alignment.End),
                            enabled = isButtonEnabled
                        )
                    }

                    if (showBottomSheet) {
                        ModalBottomSheet(
                            modifier = Modifier.fillMaxWidth(),
                            onDismissRequest = {
                                showBottomSheet = false
                            },
                            sheetState = sheetState
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .border(
                                            2.dp,
                                            MaterialTheme.colorScheme.outline,
                                            RoundedCornerShape(20.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Ubah PIN Sukses!")
                                        Text(text = "Yeay!")
                                    }
                                }
                                BaseButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, bottom = 32.dp),
                                    text = "Lanjut",
                                    onClicked = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                showBottomSheet = false
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEWalletChangePinScreen() {
    JajanManiaTheme {
        Surface {
            EWalletChangePinScreen()
        }
    }
}