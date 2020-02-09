package com.optimus.servicetest

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.optimus.servicetest.App.Companion.CHANNEL_ID

class DelayedMessageService : IntentService("DelayedMessageService") {

    companion object {
        const val EXTRA_MESSAGE = "message"
        const val NOTIFICATION_ID = 1212
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.e("M_DelayedMessageService", "Started")

        val lock = Object()
        synchronized(lock) {
            try {
                lock.wait(10000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        val text = intent?.getStringExtra(EXTRA_MESSAGE)
        Log.e("M_DelayedMessageService", "The message is: $text")
        showText(text)
    }

    private fun showText(text: String?) {

        val actionIntent = Intent(this, MainActivity::class.java)

        val actionPendingIntent = PendingIntent.getActivity(
            this,
            0,
            actionIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID )
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentTitle("Notification service")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(actionPendingIntent)
            .build()


        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)

    }


}




