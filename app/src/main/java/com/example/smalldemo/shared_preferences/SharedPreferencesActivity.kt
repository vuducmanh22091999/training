package com.example.smalldemo.shared_preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smalldemo.R
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        initListener()
    }

    private fun initListener() {
        btnSaveData.setOnClickListener {
            addData()
        }

        btnReadData.setOnClickListener {
            readData()
        }
    }

    private fun addData() {
        editor = sharedPreferences.edit()
        editor.putString("name", "Vu Duc Manh")
        editor.putInt("age", 21)
        editor.putLong("weight", 65)
        editor.putBoolean("gender", true)
        editor.apply()
        Toast.makeText(this, "Success!!!", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun readData() {
        val name = sharedPreferences.getString("name", "")
        val age = sharedPreferences.getInt("age", 0)
        val weight = sharedPreferences.getLong("weight", 0)
        val gender = sharedPreferences.getBoolean("gender", false)

        tvName.text = "Name: $name"
        tvAge.text = "Age: $age"
        tvWeight.text = "Weight: $weight"
        tvGender.text = "Gender: $gender"

    }

}
