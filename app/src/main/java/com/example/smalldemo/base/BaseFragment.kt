package com.example.smalldemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smalldemo.database.MyRoomDatabase

abstract class BaseFragment : Fragment(){
    abstract fun getLayoutId() : Int
    abstract fun doViewCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doViewCreated()
    }

    fun addFragment(fragment: Fragment, id : Int) {
        if (activity is BaseActivity){
            (activity as BaseActivity).addFragment(fragment, id)
        }
    }

    fun replaceFragment(fragment : Fragment, id : Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, id)
        }
    }

    fun initMyRoomDatabase() : MyRoomDatabase? {
        if (activity is BaseActivity) {
            return (activity as BaseActivity).initMyRoomDatabase()
        }
        return null
    }
}