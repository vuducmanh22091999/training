package com.example.smalldemo.ui_layout

import android.content.Intent
import android.os.Bundle
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseActivity
import com.example.smalldemo.fragment.FirstFragment
import com.example.smalldemo.fragment.SecondFragment
import kotlinx.android.synthetic.main.activity_demo_fragment.*

class DemoFragmentActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_demo_fragment
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        tvAddFirstFragment.setOnClickListener {
            val firstFragment = FirstFragment()
            val bundle = Bundle()
            bundle.putString("test", "Send data")
            firstFragment.arguments = bundle

            addFragment(firstFragment, R.id.flContainFragment)
        }

        tvAddSecondFragment.setOnClickListener {
            val secondFragment = SecondFragment()
            replaceFragment(secondFragment, R.id.flContainFragment)        }
    }

}
