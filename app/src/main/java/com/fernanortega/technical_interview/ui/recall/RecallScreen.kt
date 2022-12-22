package com.fernanortega.technical_interview.ui.recall

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fernanortega.technical_interview.model.network.client.RecallResponse
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecallScreen(navController: NavController, recallViewModel: RecallViewModel) {
    recallViewModel.getAllOrders()
    val result: List<RecallResponse> by recallViewModel.list.observeAsState(initial = listOf())
    val isUiLoading: Boolean by recallViewModel.isUiLoading.observeAsState(initial = false)

    Scaffold(topBar = { TopAppBar() }, bottomBar = { BottomAppBar() }) {
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
            if(isUiLoading) {
                CircularProgressIndicator()
            } else {
                if (result.isEmpty()) {
                    Text(
                        text = "No hay ordenes por el momento",
                        modifier = Modifier.align(CenterHorizontally)
                    )
                } else {
                    ListOfOrders(result)
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
fun BottomAppBar() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            CategoryButton(
                "All",
                5, modifier = Modifier.weight(1f),
                bgColor = MaterialTheme.colors.primary,
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }, icon = null
            )
            CategoryButton(
                "To go",
                1, modifier = Modifier.weight(2f),
                bgColor = Color(0xFF8ec35d),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }, icon = null
            )
            CategoryButton(
                "Dine In",
                2, modifier = Modifier.weight(2f),
                bgColor = Color(0xFFa9a9a9),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }, icon = null
            )
            CategoryButton(
                "Pick up",
                3, modifier = Modifier.weight(2f),
                bgColor = Color(0xFFffa929),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }, icon = null
            )
            CategoryButton(
                "Delivery",
                4, modifier = Modifier.weight(2f),
                bgColor = Color(0xFF1e90ff),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                },
                icon = null
            )
            CategoryButton(
                "",
                5,
                modifier = Modifier.weight(1f),
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
                    contentDescription = "Close button",
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
        Text(text = name, fontSize = 22.sp)
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
        Text(text = "Add Tip")
    }
}

@Composable
fun ListOfOrders(listResult: List<RecallResponse>) {
    var order by rememberSaveable { mutableStateOf("") }
    Column(Modifier.fillMaxWidth()) {
        TopList(order) { order = it }
        LazyColumn() {
            listResult.forEach {
                item {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(text = it.orderId.toString())
                        Text(text = it.username)
                    }
                }
            }
        }
    }
}


@Composable
fun TopList(name: String, function: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "List of orders", fontSize = 16.sp)
            Text(text = getTodayDate(), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                    Text(text = "Type the order number")
                }
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Type the order number")
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