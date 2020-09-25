package com.urmanco.openweathermap.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.urmanco.openweathermap.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModel()
    private val TAG : String = "WeatherActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.dataLoading.observe(this,{ isLoading ->
            Log.i(TAG,"Weather Activity: isLoading -> $isLoading")
            txt.text = "Loading..."
        })



        viewModel.error.observe(this,{ hasError ->
            Log.i(TAG,"Weather Activity: has Error -> $hasError")
            txt.text = "has Error"
        })

        viewModel.weather.observe(this,{weather ->
            Log.i(TAG,"Weather Activity: Result -> $weather")
            txt.text = weather.current.temp

        })

        Glide.with(this)
            .load("http://openweathermap.org/img/wn/10d@2x.png")
            .into(imageView)

    }
}