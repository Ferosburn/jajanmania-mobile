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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tokodizital.jajanmania.ui.R
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.checkbox.SimpleCheckBox
import com.tokodizital.jajanmania.ui.components.textfields.SimpleTextField
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@Composable
fun RegisterScreenCust(
    navigateToLoginScreen: () -> Unit = {},
) {

    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }
    var showConfirmPassword by remember { mutableStateOf(value = false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(
                color = Color.Transparent,
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(220.dp)
                .padding(top = 10.dp)
                .wrapContentSize(Alignment.Center),
            painter = painterResource(id = R.drawable.logo_jajan_mania),
            contentDescription = null,

            )
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            //Spacer
            Spacer(modifier = Modifier.height(70.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //title
                Text(
                    text = "Register Customer",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 155.dp, ),

                    style = MaterialTheme.typography.bodyMedium

                )
                //Spacer
                Spacer(modifier = Modifier.height(10.dp))

                SimpleTextField(
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                    value = fullname,
                    visualTransformation = VisualTransformation.None,
                    onValueChange = { newText ->
                        fullname = newText
                    },
                    placeholder = { Text(
                        "Nama Lengkap",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

                SimpleTextField(
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                    value = email,
                    visualTransformation = VisualTransformation.None,
                    onValueChange = { newText ->
                        email = newText
                    },
                    placeholder = { Text(
                        "Email",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

                SimpleTextField(
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                    value = email,
                    visualTransformation = VisualTransformation.None,
                    onValueChange = { newText ->
                        email = newText
                    },
                    placeholder = { Text(
                        "Nama Lengkap",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

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
                    value = email,
                    onValueChange = { newText ->
                        email = newText
                    },
                    placeholder = { Text(
                        "Password",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

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
                    value = email,
                    onValueChange = { newText ->
                        email = newText
                    },
                    placeholder = { Text(
                        "Konfirmasi Password",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

            }

            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier.padding(start = 30.dp),
                horizontalAlignment = Alignment.Start
            ) {
                SimpleCheckBox(text= "I agree to Terms and Conditions of Jajan Mania",
                modifier = Modifier.padding(end = 40.dp))
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                BaseButton(text = "Register", onClicked = navigateToLoginScreen)

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    androidx.compose.material3.TextButton(onClick = navigateToLoginScreen) {
                        Text(
                            text = "Click here",
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
                            style = MaterialTheme.typography.bodyMedium,
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
private fun RegisterScreenPerview() {
    JajanManiaTheme() {
        Surface {
            RegisterScreenCust()
        }
    }
}