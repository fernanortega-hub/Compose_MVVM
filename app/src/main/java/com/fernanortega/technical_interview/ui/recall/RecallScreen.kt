package com.fernanortega.technical_interview.ui.recall

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
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
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecallScreen(navController: NavController, recallViewModel: RecallViewModel) {
    val isUiLoading: Boolean by recallViewModel.isUiLoading.observeAsState(initial = false)
    val result: List<RecallModel> by recallViewModel.list.observeAsState(initial = listOf())
    val context = LocalContext.current

    if (CheckInternetConnection(context)) {
        recallViewModel.getAllOrdersFromNetwork()
    } else {
        recallViewModel.getAllOrdersFromDatabase()
        Toast.makeText(
            context,
            stringResource(id = R.string.connection_lost_message),
            Toast.LENGTH_LONG
        ).show()
    }

    Scaffold(topBar = { TopAppBar() }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                AddTipButton()
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (isUiLoading) {
                Column(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if(result.isEmpty()) {
                    Column(
                        Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = "No hay ordenes por el momento",
                            fontSize = 22.sp
                        )
                    }
                } else {
                    ListOfOrders(recallViewModel, navController)
                }
            }
        }

    }
}

@Composable
fun TopAppBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        title = { Text(fontSize = 20.sp, text = "Recall") })
}

@Composable
fun BottomAppBar(recallViewModel: RecallViewModel) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            CategoryButton(
                stringResource(id = R.string.all),
                5, modifier = Modifier.weight(1.5f),
                bgColor = MaterialTheme.colors.primary,
                onClickFunction = {
                    recallViewModel.getAllOrdersFromNetwork()
                }, icon = null
            )
            CategoryButton(
                stringResource(id = R.string.to_go),
                2, modifier = Modifier.weight(2f),
                bgColor = Color(0xFF8ec35d),
                onClickFunction = {
                    recallViewModel.getForId(it)
                }, icon = null
            )
            CategoryButton(
                stringResource(id = R.string.dine_in),
                1, modifier = Modifier.weight(2f),
                bgColor = Color(0xFFa9a9a9),
                onClickFunction = {
                    recallViewModel.getForId(it)
                }, icon = null
            )
            CategoryButton(
                stringResource(id = R.string.pick_up),
                3, modifier = Modifier.weight(2f),
                bgColor = Color(0xFFffa929),
                onClickFunction = {
                    recallViewModel.getForId(it)
                }, icon = null
            )
            CategoryButton(
                stringResource(id = R.string.delivery),
                4, modifier = Modifier.weight(2f),
                bgColor = Color(0xFF1e90ff),
                onClickFunction = {
                    recallViewModel.getForId(it)
                },
                icon = null
            )
            CategoryButton(
                "",
                6,
                modifier = Modifier.weight(1.5f),
                bgColor = MaterialTheme.colors.primary,
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }, icon = Icons.Default.AccountCircle
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(color = MaterialTheme.colors.primary),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { },
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colors.onPrimary)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close button",
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                )
            }
            TextButton(
                onClick = { },
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colors.onPrimary)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done button",
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryButton(
    name: String,
    id: Int,
    onClickFunction: (Int) -> Unit,
    bgColor: Color,
    icon: ImageVector?,
    modifier: Modifier
) {
    Button(
        onClick = { onClickFunction(id) },
        modifier = modifier
            .padding(4.dp)
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor, contentColor = Color.White)
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "icon")
        }
        Text(text = name, fontSize = 16.sp)
    }
}

@Composable
fun AddTipButton() {
    Button(onClick = { }) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add tip icon",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = stringResource(id = R.string.add_tip_label))
    }
}

@Composable
fun ListOfOrders(recallViewModel: RecallViewModel, navController: NavController) {
    val result: List<RecallModel> by recallViewModel.list.observeAsState(initial = listOf())
    var order by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        TopList(order) { order = it }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            result.forEach {
                item {
                    OrderCard(it, navController)
                }
            }
        }
        BottomAppBar(recallViewModel)
    }
}


@Composable
fun OrderCard(data: RecallModel, navController: NavController) {

    var bgColor: Color = MaterialTheme.colors.onSurface
    when (data.orderType) {
        1 -> bgColor = Color(0xFFa9a9a9)
        2 -> bgColor = Color(0xFF8ec35d)
        3 -> bgColor = Color(0xFFffa929)
        4 -> bgColor = Color(0xFF1e90ff)
    }

    Card(
        Modifier
            .fillMaxWidth().pointerInput(Unit) {
                detectTapGestures(onDoubleTap = {
                    navController.navigate(Routes.EditOrder.createRoute(data.orderId))
                })
            },
        contentColor = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.background(color = bgColor),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp, start = 12.dp, top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "${stringResource(id = R.string.order_label)}#: ${data.orderId}")
                Spacer(Modifier.width(8.dp))
                Text(text = "${stringResource(id = R.string.user_label)}: ${data.username}")
                Spacer(Modifier.width(8.dp))
                Text(text = "${stringResource(id = R.string.total_label)}: $${data.subTotal}")
                Spacer(Modifier.width(8.dp))
                Text(text = "${stringResource(id = R.string.ticket_label)}#: ${data.ticketNumber}")
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "${stringResource(id = R.string.orderTime_label)}: ${data.orderDateTime}",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp, start = 12.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                when (data.orderType) {
                    1 -> Text(text = stringResource(id = R.string.dine_in))
                    2 -> Text(text = stringResource(id = R.string.to_go))
                    3 -> Text(text = stringResource(id = R.string.pick_up))
                    4 -> Text(text = stringResource(id = R.string.delivery))
                }

                Spacer(Modifier.width(8.dp))
                Text(text = "-")
                Spacer(Modifier.width(8.dp))
                Text(text = data.username, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun TopList(name: String, function: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.list_orders_label), fontSize = 16.sp)
            Text(text = getTodayDate(), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { function(it) },
            placeholder = {
                Row(Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.order_number_label))
                }
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.order_number_label))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

fun getTodayDate(): String {
    val formatter = SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.US)
    val date = Date()
    return formatter.format(date)
}