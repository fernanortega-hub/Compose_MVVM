package com.fernanortega.technical_interview.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fernanortega.technical_interview.R
import com.fernanortega.technical_interview.ui.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {
    var inputData by rememberSaveable { mutableStateOf("") }

    BoxWithConstraints {
        if (maxWidth < 800.dp) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0f to MaterialTheme.colors.surface,
                            10000f to MaterialTheme.colors.primary
                        )
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.google),
                    contentDescription = "App image",
                    modifier = Modifier
                        .width(120.dp)
                        .padding(vertical = 0.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = inputData,
                    onValueChange = {
                        inputData = it
                    },
                    modifier = Modifier
                        .height(80.dp)
                        .width(400.dp)
                        .background(MaterialTheme.colors.surface),
                    readOnly = true,
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )
                Row(
                    Modifier
                        .width(400.dp)
                        .padding(vertical = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    KeyboardButton(text = "1", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(text = "2", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(text = "3", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })
                }
                Row(
                    Modifier
                        .width(400.dp)
                        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    KeyboardButton(text = "4", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(text = "5", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(text = "6", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })
                }
                Row(
                    Modifier
                        .width(400.dp)
                        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    KeyboardButton(text = "7", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(text = "8", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(text = "9", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })
                }
                Row(
                    Modifier
                        .width(400.dp)
                        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    KeyboardButton(
                        text = stringResource(id = R.string.clear_label),
                        modifier = Modifier.weight(1f),
                        functionClick = {
                            inputData = ""
                        })

                    KeyboardButton(text = "0", modifier = Modifier.weight(1f), functionClick = {
                        inputData += it
                    })

                    KeyboardButton(
                        text = stringResource(id = R.string.go_label),
                        modifier = Modifier.weight(1f),
                        functionClick = {
                            navController.navigate(Routes.Recall.route)
                        })
                }
                Image(
                    painterResource(id = R.drawable.google),
                    contentDescription = "Restaurant logo",
                    modifier = Modifier.width(64.dp)
                )
            }
        } else {
            Row(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            0f to MaterialTheme.colors.surface,
                            10000f to MaterialTheme.colors.primary
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(id = R.drawable.google),
                    contentDescription = "App image",
                    modifier = Modifier
                        .width(120.dp)
                        .padding(vertical = 0.dp)
                )
                Spacer(modifier = Modifier.width(124.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = inputData,
                        onValueChange = {
                            inputData = it
                        },
                        modifier = Modifier
                            .height(80.dp)
                            .width(400.dp)
                            .background(MaterialTheme.colors.surface),
                        readOnly = true,
                        singleLine = true,
                        maxLines = 1,
                        textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                    )
                    Row(
                        Modifier
                            .width(400.dp)
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        KeyboardButton(text = "1", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(text = "2", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(text = "3", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })
                    }
                    Row(
                        Modifier
                            .width(400.dp)
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        KeyboardButton(text = "4", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(text = "5", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(text = "6", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })
                    }
                    Row(
                        Modifier
                            .width(400.dp)
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        KeyboardButton(text = "7", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(text = "8", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(text = "9", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })
                    }
                    Row(
                        Modifier
                            .width(400.dp)
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        KeyboardButton(
                            text = stringResource(id = R.string.clear_label),
                            modifier = Modifier.weight(1f),
                            functionClick = {
                                inputData = ""
                            })

                        KeyboardButton(text = "0", modifier = Modifier.weight(1f), functionClick = {
                            inputData += it
                        })

                        KeyboardButton(
                            text = stringResource(id = R.string.go_label),
                            modifier = Modifier.weight(1f),
                            functionClick = {
                                navController.navigate(Routes.Recall.route)
                            })
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painterResource(id = R.drawable.google),
                        contentDescription = "Restaurant logo",
                        modifier = Modifier.width(48.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun KeyboardButton(text: String, functionClick: (String) -> Unit, modifier: Modifier) {
    Button(modifier = modifier
        .height(100.dp)
//        .width(100.dp)
        ,
        onClick = {
            functionClick(text)
        }) {
        Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
    }
}

