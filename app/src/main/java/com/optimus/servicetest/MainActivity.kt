package com.optimus.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response = "response"

        // IntentServiceTest
        btn_start_service.setOnClickListener {
            val intent = Intent(this, DelayedMessageService::class.java)
            intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, response)
            startService(intent)
        }


    }
}
