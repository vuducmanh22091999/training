package com.example.smalldemo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import com.example.smalldemo.demo_send_data.FirstActivity
import com.example.smalldemo.demo_send_data.SecondActivity
import kotlinx.android.synthetic.main.fragment_tab2.*

class TabSecondFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        if (userVisibleHint) {
            getDataToTabFirst()
//            getDataToTabSecond()
        }
        return R.layout.fragment_tab2
    }

    override fun doViewCreated() {
        initListener()
//        getDataToTabFirst()
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

    private fun getDataToTabSecond() {
        if (activity is FirstActivity) {
            tvSecondReceivedData.text = (activity as FirstActivity).getDataToTabSecond().toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TabSecondFragment().apply {
            val bundle = arguments
            if (bundle != null)
                tvFirstReceivedData.text = bundle.getString("data").toString()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed) {
            getDataToTabFirst()
//            getDataToTabSecond()
        }
    }

}