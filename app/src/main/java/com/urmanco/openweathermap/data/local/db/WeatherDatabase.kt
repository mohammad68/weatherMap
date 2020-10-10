package com.urmanco.openweathermap.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.urmanco.openweathermap.data.model.Weather

@TypeConverters(ConverterCurrent::class, ConverterDaily::class)
@Database(entities = [Weather::class],version = 1,exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){

    abstract fun weatherDao(): WeatherDao

    companion object{

        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return  tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()

                INSTANCE = instance
                return  instance
            }
        }

    }
}