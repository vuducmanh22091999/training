package com.example.smalldemo.demo_send_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smalldemo.R
import com.example.smalldemo.adapter.TabAdapter
import com.example.smalldemo.fragment.TabSecondFragment
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        initView()
    }

    private fun initView() {
        viewPager.adapter = TabAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    fun getViewPager() = viewPager

    fun sendDataFromTabFirst() {
        val tabSecondFragment = TabSecondFragment()
        val bundle = Bundle()
        bundle.putString("data", "Data from Tab First")
        tabSecondFragment.arguments = bundle
    }
}
