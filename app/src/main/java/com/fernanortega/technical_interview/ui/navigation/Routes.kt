package com.fernanortega.technical_interview.ui.navigation

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Recall: Routes("recall")
    object EditOrder: Routes("edit-order/{orderId}") {
        fun createRoute(orderId: Int) = "edit-order/$orderId"
    }
}