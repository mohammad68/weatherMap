package com.urmanco.openweathermap.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.urmanco.openweathermap.R
import com.urmanco.openweathermap.data.source.model.Daily
import com.urmanco.openweathermap.databinding.ItemWeatherBinding

class WeatherAdapter(private val daily:  List<Daily>): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_weather,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.itemWeatherBinding.daily = daily[position]
    }

    override fun getItemCount(): Int = daily.size

    inner class WeatherViewHolder(val itemWeatherBinding: ItemWeatherBinding): RecyclerView.ViewHolder(itemWeatherBinding.root)

}