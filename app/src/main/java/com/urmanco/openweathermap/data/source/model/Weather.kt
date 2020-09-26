package com.urmanco.openweathermap.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.urmanco.openweathermap.data.source.local.COLUMN_CURRENT
import com.urmanco.openweathermap.data.source.local.COLUMN_DAILY


@Entity(tableName = "weather")
data class Weather @JvmOverloads constructor (
     @PrimaryKey(autoGenerate = true) val id: Int
    ,@ColumnInfo(name = COLUMN_CURRENT) val current: Current = Current()
    ,@ColumnInfo(name = COLUMN_DAILY) val daily: List<Daily> = listOf()
)