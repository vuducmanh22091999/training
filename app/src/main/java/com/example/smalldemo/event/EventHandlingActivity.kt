package com.example.smalldemo.event

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smalldemo.R
import kotlinx.android.synthetic.main.activity_event_handling.*


class EventHandlingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_handling)

        onClick()
        onLongClick()
        onTouch()
    }

    private fun onClick() {
        tvUpSize.setOnClickListener {
            tvTestSize.textSize = 50f
        }

        tvDownSize.setOnClickListener {
            tvTestSize.textSize = 25f
        }
    }

    private fun onLongClick() {
        tvLongClick.setOnLongClickListener {
            Toast.makeText(this, "Event onLongClick has taken", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener false
        }
    }

    private fun onTouch() {
        tvOnTouch.setOnTouchListener { view, motionEvent ->
            Toast.makeText(this, "Event onTouch has taken", Toast.LENGTH_SHORT).show()
            return@setOnTouchListener true
        }
    }
}
