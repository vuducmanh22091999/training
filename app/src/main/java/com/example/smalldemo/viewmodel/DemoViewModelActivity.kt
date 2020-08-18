package com.example.smalldemo.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.smalldemo.R
import kotlinx.android.synthetic.main.activity_demo_view_model.*

class DemoViewModelActivity : AppCompatActivity() {
        var countViewModel : CountViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_view_model)

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        initListener()
        updateTextView()
    }

    private fun updateTextView() {
        tvNumberCount.text = countViewModel?.count.toString()
    }

    private fun initListener() {
        btnPlus.setOnClickListener {
            plusCountNumber()
        }

        btnMinus.setOnClickListener {
            minusCountNumber()
        }
    }

    private fun plusCountNumber() {
        countViewModel?.count = countViewModel?.count!! + 1
        updateTextView()
    }

    private fun minusCountNumber() {
        countViewModel?.count = countViewModel?.count!! - 1
        updateTextView()
    }
}