package com.example.smalldemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Insert
    fun insertData (dataRoom: DataRoom)
    @Query ("Select * from data")
    fun getData() : List<DataRoom>
}