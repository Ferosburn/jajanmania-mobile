package com.tokodizital.jajanmania.ui.components.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme
import com.tokodizital.jajanmania.ui.uicontroller.StatusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDropdown(
    items: Array<String>,
    onItemSelected: (String) -> Unit,
    textColor: Color = Color.Black,
    modifier: Modifier = Modifier,
    errorText: String = ""
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(items[0]) }

    StatusBarColor(
        color = MaterialTheme.colorScheme.background
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
                    .fillMaxWidth(),
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
                modifier = modifier.background(
                    Color.White
                ),
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        modifier = modifier.background(
                            Color.White
                        ),
                        text = { Text(text = item,
                            color = textColor) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            onItemSelected(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBaseDropdown() {
    JajanManiaTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            BaseDropdown(
                items = arrayOf("Pilih jenis kelamin", "Pria", "Wanita"),
                onItemSelected = { selectedGender ->
                },
            )
        }
    }
}