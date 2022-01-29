package com.chlqudco.develop.bmicalculator

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomRecord::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomRecordDao(): RoomDAO
}