package com.example.smalldemo.demo_send_data

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.smalldemo.R
import com.example.smalldemo.base.BaseActivity
import com.example.smalldemo.database.DataRoom
import com.example.smalldemo.database.MyRoomDatabase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_second
    }

    override fun doViewCreated() {
        initMyRoomDatabase()
        initListener()
    }

    private fun initListener() {
        btnSendDataTab2.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            intent.putExtra("data", "Data from Second Activity")
            addData()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun addData() {
        initMyRoomDatabase().getDAOData().let {
            it?.insertData(DataRoom(1 ,"Data from Second Activity_ROOM"))
            Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show()
        }
    }

}