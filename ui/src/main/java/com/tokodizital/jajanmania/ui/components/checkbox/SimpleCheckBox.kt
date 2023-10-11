package com.tokodizital.jajanmania.ui.components.checkbox

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun SimpleCheckBox(
    text: String,
    textColor: Color = Color.Black,
    modifier: Modifier
) {
    val contextForToast = LocalContext.current.applicationContext

    var checked by remember {
        mutableStateOf(false)
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked_ ->
                checked = checked_
                Toast.makeText(contextForToast, "checked_ = $checked_", Toast.LENGTH_SHORT).show()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = Color.Green,
                checkmarkColor = Color.White
            )
        )

        Text(text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
        )
    }
}