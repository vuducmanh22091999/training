package com.example.smalldemo.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smalldemo.R
import kotlinx.android.synthetic.main.activity_received_notification.*

class ReceivedNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_received_notification)

        receivedNotification()
    }

    private fun receivedNotification() {
        val title = intent.extras?.get("title").toString()
        val content = intent.extras?.get("content").toString()

        tvReceivedTitle.text = title
        tvReceivedContent.text = content
    }
}
