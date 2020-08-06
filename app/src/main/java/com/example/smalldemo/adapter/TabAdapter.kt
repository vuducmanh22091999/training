package com.example.smalldemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.smalldemo.fragment.TabFirstFragment
import com.example.smalldemo.fragment.TabSecondFragment

class TabAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val listTab = arrayOf("Tab First", "Tab Second")

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            TabFirstFragment()
        } else
            TabSecondFragment()
    }

    override fun getCount(): Int {
        return listTab.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTab[position]
    }
}