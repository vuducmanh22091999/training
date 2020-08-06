package com.example.smalldemo.fragment

import android.os.Bundle
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import com.example.smalldemo.demo_send_data.FirstActivity
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.fragment_tab1.*

class TabFirstFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_tab1
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        btnSendDataTab1.setOnClickListener {
            if (activity is FirstActivity) {
//                sendDataFromTabFirst()
                (activity as FirstActivity).sendDataFromTabFirst()
                (activity as FirstActivity).getViewPager().currentItem = 1
            }
        }
    }

    private fun sendDataFromTabFirst() {
        val tabSecondFragment = TabSecondFragment()
        val bundle = Bundle()
        bundle.putString("data", "Data from Tab First")
        tabSecondFragment.arguments = bundle
    }
}