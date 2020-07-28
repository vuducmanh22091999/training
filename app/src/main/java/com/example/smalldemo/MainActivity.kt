package com.example.smalldemo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.smalldemo.broadcast.MyBroadcast
import com.example.smalldemo.content_provider.ShowImageActivity
import com.example.smalldemo.service.MyService
import com.example.smalldemo.ui_layout.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    lateinit var myBroadcast: MyBroadcast

    private var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniListener()
//        initBroadcast()

    }

    private fun iniListener() {
//        actMain_tvSendMessage.setOnClickListener {
//            composeMmsMessage(actMain_etText.text.toString())
////            dialPhoneNumber("0909090909")
//        }
//
//        actMain_tvStarService.setOnClickListener {
//            checkPermission()
//
//        }
//
//        actMain_tvStopService.setOnClickListener {
//            stopService(Intent(baseContext, MyService::class.java))
//        }
//
//        actMain_tvAdd.setOnClickListener {
//            sendDataToBroadcast()
//        }

        tvConstraintLayout.setOnClickListener {
            val intentConstraintLayout = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intentConstraintLayout)
        }

        tvLinearLayout.setOnClickListener {
            val intentLinearLayout = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intentLinearLayout)
        }

        tvFrameLayout.setOnClickListener {
            val intentFrameLayout = Intent(this, FrameLayoutActivity::class.java)
            startActivity(intentFrameLayout)
        }

        tvRelativeLayout.setOnClickListener {
            val intentRelativeLayout = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intentRelativeLayout)
        }

        tvTableLayout.setOnClickListener {
            val intentTableLayout = Intent(this, TableLayoutActivity::class.java)
            startActivity(intentTableLayout)
        }

        tvGridLayout.setOnClickListener {
            val intentGridLayout = Intent(this, GridLayoutActivity::class.java)
            startActivity(intentGridLayout)
        }

        tvContentProvider.setOnClickListener {
            val intentShowImage = Intent(this, ShowImageActivity::class.java)
            startActivity(intentShowImage)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermissions(baseContext, PERMISSIONS)) {
                requestPermissions(
                    PERMISSIONS, 123
                )
                return
            }
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        startService(Intent(baseContext, MyService::class.java))

    }

    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startService(Intent(baseContext, MyService::class.java))
            } else {
                Toast.makeText(baseContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun composeMmsMessage(message: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra("sms_body", message)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun initBroadcast() {
        myBroadcast = MyBroadcast()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_PLUS_NUMBER)
        registerReceiver(myBroadcast, intentFilter)
    }

//    private fun sendDataToBroadcast() {
//        val a = actMain_etNumberA.text.toString().toInt()
//        val b = actMain_etNumberB.text.toString().toInt()
//        val intent = Intent().apply {
//            action = ACTION_PLUS_NUMBER
//            putExtra(NUMBER_A, a)
//            putExtra(NUMBER_B, b)
//        }
//        sendBroadcast(intent)
//    }

    override fun onDestroy() {
        unregisterReceiver(myBroadcast)
        super.onDestroy()
    }

}

