package com.optimus.servicetest

import android.app.IntentService
import android.content.Intent
import android.util.Log
class DelayedMessageService : IntentService("DelayedMessageService") {

    companion object{
        const val EXTRA_MESSAGE = "message"
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

        val text =intent?.getStringExtra(EXTRA_MESSAGE)
        Log.e("M_DelayedMessageService", "The message is: $text")
    }


}




