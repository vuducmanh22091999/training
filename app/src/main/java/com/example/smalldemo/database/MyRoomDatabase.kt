package com.example.smalldemo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataRoom::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun getDAOData() : DataDao?
}