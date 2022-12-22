package com.fernanortega.technical_interview.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fernanortega.technical_interview.ui.login.LoginScreen
import com.fernanortega.technical_interview.ui.recall.RecallScreen
import com.fernanortega.technical_interview.ui.recall.RecallViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(), firstDestination: String = Routes.Login.toString()) {

}