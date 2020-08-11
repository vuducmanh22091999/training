package com.example.smalldemo.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import com.example.smalldemo.database.DataRoom
import com.example.smalldemo.database.MyRoomDatabase
import com.example.smalldemo.demo_send_data.FirstActivity
import com.example.smalldemo.demo_send_data.SecondActivity
import kotlinx.android.synthetic.main.fragment_tab2.*

class TabSecondFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        if (userVisibleHint) {
            getDataToTabFirst()
        }
        return R.layout.fragment_tab2
    }

    override fun doViewCreated() {
        initMyRoomDatabase()
        initListener()
    }

    private fun initListener() {
        btnStartActivityWithoutDestroy.setOnClickListener {
            val intentActivityWithoutDestroy = Intent(context, SecondActivity::class.java)
            startActivityForResult(intentActivityWithoutDestroy, 101)
        }

        btnStartActivityDestroy.setOnClickListener {
            val intentActivityDestroy = Intent(context, SecondActivity::class.java)
            startActivity(intentActivityDestroy)
            activity?.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                val test = data?.getStringExtra("data")
                tvSecondReceivedData.text = test
                getDataToTabSecond()
            }
        }
    }

    private fun getDataToTabFirst() {
        if (activity is FirstActivity) {
            tvFirstReceivedData.text = (activity as FirstActivity).getData()
        }
    }

    private fun getDataToTabSecond() {
        initMyRoomDatabase()?.let {
            val listDataRoom = it.getDAOData()?.getData()
            tvFirstReceivedData.text = listDataRoom?.get(0)?.dataSend ?: ""
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