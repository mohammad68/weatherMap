package com.urmanco.openweathermap.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.urmanco.openweathermap.R
import com.urmanco.openweathermap.databinding.ActivityWeatherBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Loading,failed and refresh not implemented

        val binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this,R.layout.activity_weather)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //RecyclerView Adapter
        viewModel.weather.observe(this,{ weather -> binding.weatherAdapter = WeatherAdapter(weather.daily) })

        //Toast success message
        viewModel.successMessage.observe(this,{message -> Toast.makeText(this,message,Toast.LENGTH_SHORT).show()})




    }
}

