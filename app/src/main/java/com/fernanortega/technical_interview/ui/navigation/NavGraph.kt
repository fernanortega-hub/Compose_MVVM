package com.fernanortega.technical_interview.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fernanortega.technical_interview.model.domain.RecallModel
import com.fernanortega.technical_interview.ui.edit_order.EditScreen
import com.fernanortega.technical_interview.ui.edit_order.EditViewModel
import com.fernanortega.technical_interview.ui.login.LoginScreen
import com.fernanortega.technical_interview.ui.recall.RecallScreen
import com.fernanortega.technical_interview.ui.recall.RecallViewModel

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun NavGraph(navController: NavHostController = rememberNavController(), firstDestination: String = Routes.Login.route) {
    NavHost(navController = navController, startDestination = firstDestination) {
        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
        composable(Routes.Recall.route) {
            val recallViewModel = hiltViewModel<RecallViewModel>()
            RecallScreen(navController, recallViewModel)
        }
        composable(Routes.EditOrder.route, arguments = listOf(navArgument("orderId") { type = NavType.IntType })) {
            val editViewModel = hiltViewModel<EditViewModel>()
            editViewModel.getOrderData(it.arguments?.getInt("orderId") ?: 0)
            EditScreen(navController, editViewModel)
        }
    }
}