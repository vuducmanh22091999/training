package com.example.smalldemo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_first
    }

    override fun doViewCreated() {
        getData()
        initListener()
    }

    private fun initListener() {
        tvFirstFragment.setOnClickListener {
            val secondFragment = SecondFragment()
            val bundle = Bundle()
            bundle.putString("test", "ABC")
            secondFragment.arguments = bundle
            replaceFragment(secondFragment, R.id.flContainFragment)
        }
    }

    private fun getData() {
        val bundle = arguments
        if (bundle != null) {
            tvFirstFragment.text = bundle.getString("test")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("testFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("testFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        Log.d("testFragment", "onCreateView")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("testFragment", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("testFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("testFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("testFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("testFragment", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("testFragment", "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("testFragment", "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("testFragment", "onDetach")
    }


}