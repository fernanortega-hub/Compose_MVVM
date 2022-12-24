package com.fernanortega.technical_interview.ui.edit_order

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fernanortega.technical_interview.R
import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.ui.helpers.CheckInternetConnection
import com.fernanortega.technical_interview.ui.navigation.Routes

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditScreen(navController: NavController, viewModel: EditViewModel) {
    val result: RecallModel by viewModel.data.observeAsState(
        initial = RecallModel(
            1, "Fer", 80.0, 21, "23/12/2022", 1
        )
    )

    val context = LocalContext.current

    var username by rememberSaveable { mutableStateOf("") }
    var orderType by rememberSaveable { mutableStateOf("") }
    var subTotal by rememberSaveable { mutableStateOf("") }

    when (result.orderType) {
        1 -> orderType = stringResource(id = R.string.dine_in)
        2 -> orderType = stringResource(id = R.string.to_go)
        3 -> orderType = stringResource(id = R.string.pick_up)
        4 -> orderType = stringResource(id = R.string.delivery)
    }

    Scaffold(Modifier.fillMaxSize(), topBar = {
        TopEditBar(navController)
    }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.edit_order_instructions),
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp
            )
            OutlinedTextField(modifier = Modifier.width(480.dp),
                value = result.orderId.toString(),
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.order_label).plus(" #")
                    )
                })
            OutlinedTextField(
                modifier = Modifier.width(480.dp),
                value = result.orderDateTime,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.orderTime_label)
                    )
                })
            OutlinedTextField(
                modifier = Modifier.width(480.dp),
                value = orderType,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.order_type_label)
                    )
                })
            OutlinedTextField(
                modifier = Modifier.width(480.dp),
                value = result.ticketNumber.toString(),
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.ticket_label).plus(" #")
                    )
                })
            OutlinedTextField(
                modifier = Modifier.width(480.dp),
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.user_label)
                    )
                })
            OutlinedTextField(
                modifier = Modifier.width(480.dp),
                value = subTotal,
                onValueChange = {
                    subTotal = it
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.total_label)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Row(Modifier.width(480.dp), horizontalArrangement = spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { navController.navigate(Routes.Recall.route) },
                    Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.cancel_label))
                }
                Button(onClick = {
                    if (username.isEmpty() || subTotal.isEmpty()) {
                        Toast.makeText(context, R.string.check_user_total_info, Toast.LENGTH_LONG)
                            .show()
                    } else {
                        if (CheckInternetConnection(context)) {
                            try {
                                viewModel.updateOrder(
                                    RecallModel(
                                        result.orderId,
                                        username,
                                        subTotal.toDouble(),
                                        result.ticketNumber,
                                        result.orderDateTime,
                                        result.orderType
                                    )
                                )
                            } catch (error: Exception) {
                                Toast.makeText(context, R.string.check_total_format, Toast.LENGTH_LONG)
                                    .show()
                            }
                            navController.navigate(Routes.Recall.route)
                        } else {
                            Toast.makeText(
                                context,
                                R.string.check_your_connection_body,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }, Modifier.weight(1f)) {
                    Text(text = stringResource(id = R.string.edit_confirmation_label))
                }
            }
        }
    }
}

@Composable
fun TopEditBar(navController: NavController) {
    TopAppBar(modifier = Modifier.fillMaxWidth(),
        title = { Text(fontSize = 20.sp, text = stringResource(id = R.string.edit_order_label)) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Routes.Recall.route) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
            }
        })
}
