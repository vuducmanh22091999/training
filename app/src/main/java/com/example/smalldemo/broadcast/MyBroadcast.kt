package com.example.smalldemo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.smalldemo.ACTION_PLUS_NUMBER
import com.example.smalldemo.NUMBER_A
import com.example.smalldemo.NUMBER_B

class MyBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action){
            ACTION_PLUS_NUMBER -> {
                val a : Int = intent.getIntExtra(NUMBER_A, 0)
                val b : Int = intent.getIntExtra(NUMBER_B, 0)
                Toast.makeText(context, "Total: ${a+b}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}