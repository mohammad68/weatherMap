package com.urmanco.openweathermap.data.source.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather")
data class Weather @JvmOverloads constructor (
     @PrimaryKey(autoGenerate = true) val id: Int
    ,@ColumnInfo(name = "current") val current: Current = Current()
    ,@ColumnInfo(name = "daily") val daily: List<Daily> = listOf()
)