package com.tokodizital.jajanmania.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun MenuButtonSwitch(
    imageVector: ImageVector,
    menuTitle: String,
    menuDescription: String,
    isChecked: Boolean,
    onSwitchChanged: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick)

    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "Button Icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = 8.dp)
                ){
                    Text(
                        text = menuTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(16.toFloat(), TextUnitType.Sp)
                    )
                    if (menuDescription.isNotBlank()) {
                        Text(
                            text = menuDescription,
                            fontSize = TextUnit(10.toFloat(), TextUnitType.Sp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Switch(
                        checked = isChecked,
                        onCheckedChange = { newValue -> onSwitchChanged(newValue) },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp)
                    )
                }
            }
            Divider()
        }

    }
}

@Preview
@Composable
private fun Preview() {
    JajanManiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            var isSwitchChecked by remember { mutableStateOf(false) }
            Column {
                MenuButtonSwitch(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_history),
                    menuTitle = "Riwayat Transaksi",
                    menuDescription = "Lihat riwayat transaksi",
                    isChecked = isSwitchChecked,
                    onSwitchChanged = {newValue -> isSwitchChecked = newValue}
                ) {

                }
            }
        }

    }
}