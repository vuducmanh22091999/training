package com.example.smalldemo.database

import androidx.room.Entity

@Entity(tableName = "data")
data class DataRoom (var data : String = "")