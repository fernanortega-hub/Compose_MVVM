package com.fernanortega.technical_interview.ui.navigation

sealed class Routes(route: String) {
    object Login: Routes("login")
    object Recall: Routes("recall")
    object EditOrder: Routes("edit-order")
}