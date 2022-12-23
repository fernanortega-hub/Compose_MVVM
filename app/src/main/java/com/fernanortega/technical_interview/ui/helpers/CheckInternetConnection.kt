package com.fernanortega.technical_interview.ui.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
fun CheckInternetConnection(context: Context) : Boolean {
    val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val capabilities = connection.getNetworkCapabilities(connection.activeNetwork)

    if (capabilities != null) {
        return if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            true
        } else capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    return false
}