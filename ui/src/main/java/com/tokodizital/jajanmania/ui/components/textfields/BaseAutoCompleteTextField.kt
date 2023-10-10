package com.tokodizital.jajanmania.ui.components.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun BaseAutoCompleteTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String,
    items: List<String> = emptyList(),
    onItemClicked: (String) -> Unit
) {

    var showItems by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        AutoCompleteTextField(
            value = value,
            label = label,
            placeholder = placeholder,
            onFocus = { showItems = true },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = showItems,
            onDismissRequest = { showItems = !showItems },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            items.forEach { item ->
                DropdownMenuItem(text = {
                    Text(text = item)
                }, onClick = {
                    showItems = false
                    onItemClicked(item)
                })
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String,
    onFocus: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null
            )
        },
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    onFocus()
                }
            }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseAutoCompleteTextField() {
    JajanManiaTheme {
        Surface {
            val focusManager = LocalFocusManager.current
            var value by remember { mutableStateOf("") }
            val items = remember {
                listOf(
                    "Bakso",
                    "Cilok",
                    "Seblak",
                    "Sempol"
                )
            }
            BaseAutoCompleteTextField(
                value = value,
                label = "Kategori",
                placeholder = "Masukan kategori produk",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                items = items,
                onItemClicked = {
                    value = it
                    focusManager.clearFocus()
                }
            )
        }
    }
}