package com.fernanortega.technical_interview.ui.recall

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fernanortega.technical_interview.ui.theme.Technical_InterviewTheme
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecallScreen(navController: NavController) {
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
            ListOfOrders()
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
        Row() {
            CategoryButton(
                "All",
                5,
                modifier = Modifier.background(color = MaterialTheme.colors.primary),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }
            )
            CategoryButton(
                "To go",
                1,
                modifier = Modifier.background(color = Color.Green),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }
            )
            CategoryButton(
                "Dine In",
                2,
                modifier = Modifier.background(color = Color.LightGray),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }
            )
            CategoryButton(
                "Pick up",
                3,
                modifier = Modifier.background(color = Color.Yellow),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }
            )
            CategoryButton(
                "Delivery",
                4,
                modifier = Modifier.background(color = Color.Blue),
                onClickFunction = {
                    Log.i("probando clicks", it.toString())
                }
            )
        }
    }
}

@Composable
fun CategoryButton(name: String, id: Int, onClickFunction: (Int) -> Unit, modifier: Modifier) {
    Button(onClick = { onClickFunction(id) }, modifier = modifier.padding(8.dp)) {
        Text(text = name)
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
fun ListOfOrders() {
    var order by rememberSaveable { mutableStateOf("") }

    Column(Modifier.fillMaxWidth()) {
        TopList(order) { order = it }
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

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun RecallPreview() {
    Technical_InterviewTheme {
        RecallScreen(navController = NavController(LocalContext.current))
    }
}