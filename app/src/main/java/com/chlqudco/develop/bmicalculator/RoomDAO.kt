package com.chlqudco.develop.bmicalculator

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDAO {
    @Query("SELECT * FROM room_record")
    fun getAll(): List<RoomRecord>

    @Insert
    fun insert(record: RoomRecord)

    @Delete
    fun delete(memo: RoomRecord)
}