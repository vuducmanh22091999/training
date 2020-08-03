package com.example.smalldemo.notification

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.smalldemo.CHANNEL_1_ID
import com.example.smalldemo.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    private lateinit var notificationManagerCompat: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        initListener()
        this.notificationManagerCompat = NotificationManagerCompat.from(this)
    }

    private fun initListener() {
        tvSendNotification.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification() {
        val title = etTitle.text.toString()
        val content = etContent.text.toString()

        val notificationCompat = NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .build()

        val intentReceivedNotification = Intent(this, ReceivedNotificationActivity::class.java)
        intentReceivedNotification.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intentReceivedNotification.putExtra("title", title)
        intentReceivedNotification.putExtra("content", content)

        val pendingIntent = PendingIntent.getActivity(this,
            0,
            intentReceivedNotification,
            PendingIntent.FLAG_UPDATE_CURRENT)

        notificationCompat.contentIntent = pendingIntent

        this.notificationManagerCompat.notify(1, notificationCompat)
    }
}
