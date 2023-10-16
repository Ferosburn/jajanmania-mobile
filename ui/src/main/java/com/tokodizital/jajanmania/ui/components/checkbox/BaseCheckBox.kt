package com.tokodizital.jajanmania.ui.components.checkbox

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun BaseCheckBox(
    checked: Boolean = false,
    text: String,
    onCheckedChanged: (Boolean) -> Unit = {},
    textColor: Color = Color.Black,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.offset(x = (-12).dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChanged,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = Color.Green,
                checkmarkColor = Color.White
            )
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview
@Composable
fun PreviewBaseCheckBox() {
    JajanManiaTheme {
        Surface {
            var checked by remember { mutableStateOf(false) }
            BaseCheckBox(
                text = "Test",
                checked = checked,
                onCheckedChanged = { checked = it },
            )
        }
    }
}
