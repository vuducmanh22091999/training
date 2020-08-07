package com.example.smalldemo.fragment

import android.content.Intent
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import com.example.smalldemo.demo_send_data.FirstActivity
import com.example.smalldemo.demo_send_data.SecondActivity
import kotlinx.android.synthetic.main.fragment_tab2.*

class TabSecondFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_tab2
    }

    override fun doViewCreated() {
        initListener()
        getDataToTabFirst()
    }

    private fun initListener() {
        btnStartActivityWithoutDestroy.setOnClickListener {
            val intentActivityWithoutDestroy = Intent(context, SecondActivity::class.java)
            startActivity(intentActivityWithoutDestroy)
        }

        btnStartActivityDestroy.setOnClickListener {
            val intentActivityDestroy = Intent(context, SecondActivity::class.java)
            startActivity(intentActivityDestroy)
            activity?.finish()
        }
    }

    private fun getDataToTabFirst() {
        if (activity is FirstActivity) {
            tvFirstReceivedData.text = (activity as FirstActivity).getData()
        }
    }

}