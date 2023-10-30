package com.tokodizital.jajanmania.customer.ewallet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.components.appbars.DetailTopAppBar
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextField
import com.tokodizital.jajanmania.ui.components.textfields.BaseOutlinedTextFieldType
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme


@ExperimentalMaterial3Api
@Composable
fun EwalletTopUpScreen(
    
) {
    var nominal by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DetailTopAppBar(title = "Isi Saldo")
        },bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                BaseButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(com.tokodizital.jajanmania.ui.R.string.label_topUp),
                    onClicked = { /*TODO here*/},
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "Pilih Nominal",
                    modifier = Modifier.padding(top = 8.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { /* TODO HERE */  },
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(8),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White,
                            ),
                            content = {
                                Text(
                                    text = "Rp. 20.000",
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        )

                        OutlinedButton(
                            onClick = { /* TODO HERE */  },
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(8),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White,
                            ),
                            content = {
                                Text(
                                    text = "Rp. 200.000",
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        )


                }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { /* TODO HERE */ },
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(8),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White,
                            ),
                            content = {
                                Text(
                                    text = "Rp. 50.000",
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        )

                        OutlinedButton(
                            onClick = { /* TODO HERE */  },
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(8),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White,
                            ),
                            content = {
                                Text(
                                    text = "Rp. 300.000",
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        )


                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { /* Do something */ },
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(8),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White,
                            ),
                            content = {
                                Text(
                                    text = "Rp. 100.000",
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        )
                        OutlinedButton(
                            onClick = { /* Do something */ },
                            shape = RoundedCornerShape(8),
                            border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White,
                            ),
                            content = {
                                Text(
                                    text = "Rp. 500.000",
                                    style = TextStyle(
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        )


                    }
                }
            }
            item{
                BaseOutlinedTextField(
                    value = nominal,
                    onValueChanged = {/* TODO HERE */},
                    label = "Nominal",
                    placeholder = "0",
                    singleLine = true,
                    type = BaseOutlinedTextFieldType.WithClearIcon,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEWalletTopUP() {
    JajanManiaTheme {
        Surface {
            EwalletTopUpScreen()
        }
    }
}