package com.tokodizital.jajanmania.customer.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BlackButton
import com.tokodizital.jajanmania.ui.components.checkbox.SimpleCheckBox
import com.tokodizital.jajanmania.ui.components.textfields.SimpleTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun LoginScreenCust() {

    var username by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 30.dp)
            .background(
                color = Color.Transparent,
            )
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(180.dp)
                .padding(top = 50.dp)
                .wrapContentSize(Alignment.Center),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,

        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.Start
        ) {

            //Spacer
            Spacer(modifier = Modifier.height(140.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //title
                Text(
                    text = "Login Customer",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 50.dp, ),

                    style = MaterialTheme.typography.bodyMedium
                )
                //Spacer
                Spacer(modifier = Modifier.height(40.dp))

                SimpleTextField(
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                    value = username,
                    visualTransformation = VisualTransformation.None,
                    onValueChange = { newText ->
                        username = newText
                    },
                    placeholder = { Text(
                        "Username",
                        style = MaterialTheme.typography.bodyMedium
                    )
                                  },
                )

                Spacer(modifier = Modifier.height(20.dp))

                SimpleTextField(
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
                    visualTransformation = if (showPassword) {

                        VisualTransformation.None

                    } else {

                        PasswordVisualTransformation()

                    },
                    trailingIcon = {
                        if (showPassword) {
                            IconButton(onClick = { showPassword = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide_password"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { showPassword = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "hide_password"
                                )
                            }
                        }
                    },
                    value = username,
                    onValueChange = { newText ->
                        username = newText
                    },
                    placeholder = { Text(
                        "Password",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    },
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier.padding(start = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                SimpleCheckBox(
                    text= "Remember me",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                BlackButton(text = "Login")

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Don't have any account?",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    androidx.compose.material3.TextButton(onClick = {


                    }) {
                        Text(
                            text = "Create here",
                            fontSize = 15.sp,
                            modifier = Modifier.drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()
                                drawLine(
                                    color = Color.Green,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            },
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = Color.Green
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    androidx.compose.material3.TextButton(onClick = {


                    }) {
                        Text(
                            text = "Click here",
                            fontSize = 15.sp,
                            modifier = Modifier.drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()
                                drawLine(
                                    color = Color.Green,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            },
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = Color.Green
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    JajanManiaTheme() {
        Surface {
            LoginScreenCust()
        }
    }
}