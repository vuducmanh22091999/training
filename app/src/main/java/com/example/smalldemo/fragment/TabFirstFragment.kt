package com.example.smalldemo.fragment

import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import com.example.smalldemo.demo_send_data.FirstActivity
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
                sendDataFromTabFirst()
                (activity as FirstActivity).getViewPager().currentItem = 1
            }
        }
    }

    private fun sendDataFromTabFirst() {
        if (activity is FirstActivity) {
            (activity as FirstActivity).setData("Data from Tab First")
        }
    }
}