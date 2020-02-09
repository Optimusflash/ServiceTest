package com.optimus.servicetest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

/**
 * Created by Dmitriy Chebotar on 09.02.2020.
 */

class App: Application(){

    companion object{
        const val CHANNEL_ID = "serviceChanel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChanel()
    }

    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Example service channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            getSystemService(NotificationManager::class.java).apply {
                this?.createNotificationChannel(serviceChannel)
            }
        }
    }
}