package com.tokodizital.jajanmania.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun OutlineDropdownMenuBox(
    modifier: Modifier = Modifier,
    selectedOption: String = "",
    options: List<String>,
    label: String = "",
    placeholder: String = "",
    onOptionSelected: (String) -> Unit = {},
    errorText: String = ""
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOption,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = modifier
                .menuAnchor(),
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
            isError = errorText.isNotBlank(),
            supportingText = {
                if (errorText.isNotBlank()){
                    Text(text = errorText,
                        color = MaterialTheme.colorScheme.error)
                }
            },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        onOptionSelected(selectionOption)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewOutlineDropdownMenuBox() {
    JajanManiaTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val options = listOf("Jenis Kelamin", "Male", "Female")
            OutlineDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                options = options,
                label = "Jenis Kelamin",
                placeholder = "Pilih Jenis Kelamin"
            )
        }
    }
}
