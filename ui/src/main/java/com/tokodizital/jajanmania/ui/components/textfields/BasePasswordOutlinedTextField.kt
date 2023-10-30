package com.tokodizital.jajanmania.ui.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun BasePasswordOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorText: String = ""
) {

    var isShowPassword by remember { mutableStateOf(false) }

    val visualTransformation = remember(key1 = isShowPassword) {
        if (isShowPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    }
    val passwordTrailingIcon = remember(key1 = isShowPassword) {
        if (isShowPassword) {
            Icons.Filled.VisibilityOff
        } else {
            Icons.Filled.Visibility
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        label = {
            if (label.isNotEmpty()) {
                Text(text = label)
            }
        },
        placeholder = {
            if (placeholder.isNotEmpty()) {
                Text(text = placeholder)
            }
        },
        trailingIcon = {
            IconButton(onClick = { isShowPassword = !isShowPassword }) {
                Icon(
                    imageVector = passwordTrailingIcon,
                    contentDescription = "Clear text"
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        isError = errorText.isNotBlank(),
        supportingText = {
            if (errorText.isNotBlank()){
                Text(text = errorText,
                    color = MaterialTheme.colorScheme.error)
            }
        },
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBasePasswordOutlinedTextField() {
    JajanManiaTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            var value by remember { mutableStateOf("") }
            BasePasswordOutlinedTextField(
                value = value,
                onValueChanged = { value = it },
                modifier = Modifier.fillMaxWidth(),
                label = "Nama Produk",
                placeholder = "Masukan nama produk",
            )
        }
    }
}
