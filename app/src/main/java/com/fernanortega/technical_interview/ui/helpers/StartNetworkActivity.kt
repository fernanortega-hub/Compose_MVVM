package com.fernanortega.technical_interview.ui.helpers

import android.content.Context
import android.content.Intent
import android.provider.Settings

fun StartNetworkActivity(context: Context) : Unit {
    return context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
}