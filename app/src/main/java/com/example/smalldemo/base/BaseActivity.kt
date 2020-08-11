package com.example.smalldemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.smalldemo.database.MyRoomDatabase

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var myRoomDatabase : MyRoomDatabase

    abstract fun getLayoutId() : Int
    abstract fun doViewCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        doViewCreated()
    }

    fun addFragment(fragment: Fragment, id : Int) {
        supportFragmentManager.beginTransaction()
            .add(id, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment (fragment: Fragment, id : Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    fun initMyRoomDatabase() : MyRoomDatabase {
        if (!this::myRoomDatabase.isInitialized){
            myRoomDatabase = Room.databaseBuilder(applicationContext, MyRoomDatabase::class.java, "testRoom")
                .allowMainThreadQueries()
                .build()
        }
        return myRoomDatabase
    }
}