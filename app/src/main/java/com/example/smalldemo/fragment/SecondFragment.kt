package com.example.smalldemo.fragment

import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : BaseFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_second
    }

    override fun doViewCreated() {
        val bundle = arguments
        if (bundle != null){
            tvSecondFragment.text = bundle.getString("test")
        }
    }

}