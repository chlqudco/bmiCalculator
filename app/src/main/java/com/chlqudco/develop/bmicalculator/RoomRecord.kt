package com.chlqudco.develop.bmicalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_record")
class RoomRecord{
    constructor(result: Int, datetime: Long) {
        this.result = result
        this.datetime = datetime
    }

    @PrimaryKey(autoGenerate = true) var id: Int?=null
    @ColumnInfo var result: Int = 0
    @ColumnInfo var datetime: Long = 0
}