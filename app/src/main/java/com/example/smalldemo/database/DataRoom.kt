package com.example.smalldemo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataRoom (@PrimaryKey(autoGenerate = true) val idData : Int = 0, var dataSend : String = "")