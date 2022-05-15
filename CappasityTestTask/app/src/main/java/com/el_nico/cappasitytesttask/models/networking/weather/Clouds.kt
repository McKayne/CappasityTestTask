package com.el_nico.cappasitytesttask.models.networking.weather

import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all") var all: Int,
)