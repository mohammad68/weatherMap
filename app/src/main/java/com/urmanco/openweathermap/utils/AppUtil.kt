package com.urmanco.openweathermap.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urmanco.openweathermap.data.source.remote.BASE_URL_IMAGES

object AppUtil {

    @JvmStatic
    @BindingAdapter("weatherImage")
    fun loadImage(view: ImageView, url: String?){
        val iconUrl = "$BASE_URL_IMAGES$url@2x.png"
        Glide.with(view.context)
            .load(iconUrl)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?){
        this.run {
            if(adapter!= null){
                this.setHasFixedSize(true)
                this.adapter = adapter
                this.layoutManager = LinearLayoutManager(this.context)
            }

        }
    }
}