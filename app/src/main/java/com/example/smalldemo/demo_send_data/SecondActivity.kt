package com.example.smalldemo.demo_send_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smalldemo.R
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initListener()
    }

    private fun initListener() {
        btnSendDataTab2.setOnClickListener {
            viewPager.currentItem = 1
            finish()
        }
    }
}
