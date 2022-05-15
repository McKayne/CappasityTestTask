package com.el_nico.cappasitytesttask.models.networking.weather

import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("speed") var speed: Double,
    @SerializedName("deg") var deg: Int,
    @SerializedName("gust") var gust: Double
)