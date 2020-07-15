package com.example.smalldemo.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.LocationManager
import android.os.IBinder
import android.widget.Toast


class MyService : Service() {

    private var locationManager: LocationManager? = null
    private var criteria: Criteria? = null
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        criteria = Criteria()
        criteria?.let {
            it.accuracy = Criteria.ACCURACY_COARSE //default
            it.isCostAllowed = false
            locationManager?.let { manager ->
                val location = manager.getLastKnownLocation(
                    manager.getBestProvider(it, false) ?: ""
                )
                Toast.makeText(this, "latitude " + location.latitude, Toast.LENGTH_LONG).show()
                Toast.makeText(this, "longtitude" + location.longitude, Toast.LENGTH_LONG).show()
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show()
    }
}
