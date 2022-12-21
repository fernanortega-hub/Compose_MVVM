package com.fernanortega.technical_interview.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fernanortega.technical_interview.ui.login.LoginScreen
import com.fernanortega.technical_interview.ui.recall.RecallScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(), firstDestination: String = Routes.Login.toString()) {
    NavHost(navController = navController, startDestination = firstDestination) {
        composable(Routes.Login.toString()) {
            LoginScreen(navController)
        }
        composable(Routes.Recall.toString()) {
            RecallScreen(navController)
        }
    }
}