package com.fernanortega.technical_interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fernanortega.technical_interview.ui.navigation.Routes
import com.fernanortega.technical_interview.ui.login.LoginScreen
import com.fernanortega.technical_interview.ui.navigation.NavGraph
import com.fernanortega.technical_interview.ui.recall.RecallScreen
import com.fernanortega.technical_interview.ui.recall.RecallViewModel
import com.fernanortega.technical_interview.ui.theme.Technical_InterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Technical_InterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val firstDestination: String = Routes.Login.toString()
                    NavHost(navController = navController, startDestination = firstDestination) {
                        composable(Routes.Login.toString()) {
                            LoginScreen(navController)
                        }
                        composable(Routes.Recall.toString()) {
                            val recallViewModel = hiltViewModel<RecallViewModel>()
                            RecallScreen(navController, recallViewModel)

                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Technical_InterviewTheme {
        LoginScreen(navController = NavController(LocalContext.current))
    }
}