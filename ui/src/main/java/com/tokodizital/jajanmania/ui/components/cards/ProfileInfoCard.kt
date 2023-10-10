package com.tokodizital.jajanmania.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun ProfileInfoCard(
    name: String,
    phone: String,
    email: String,
    level: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleImageCard(
            90,
            painterResource(id = R.drawable.placeholder)
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20.toFloat(), TextUnitType.Sp)
            )
            Text(
                text = phone,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(14.toFloat(), TextUnitType.Sp)
            )
            Text(
                text = email,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(14.toFloat(), TextUnitType.Sp)
            )
            Text(
                text = level,
                fontSize = TextUnit(14.toFloat(), TextUnitType.Sp)
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    JajanManiaTheme {
        Surface {
            var profileName = rememberSaveable { mutableStateOf("Elon Musk") }
            var profilePhone = rememberSaveable { mutableStateOf("+62 852 6161 6161") }
            var profileEmail = rememberSaveable { mutableStateOf("ElonMusk@twitter.com") }
            var profileLevel = rememberSaveable { mutableStateOf("Level 1") }


            ProfileInfoCard(
                profileName.value,
                profilePhone.value,
                profileEmail.value,
                profileLevel.value
            )
        }
    }
}