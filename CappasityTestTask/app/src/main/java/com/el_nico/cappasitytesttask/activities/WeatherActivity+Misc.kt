package com.el_nico.cappasitytesttask.activities

import android.graphics.Bitmap
import com.google.android.material.snackbar.Snackbar

fun WeatherActivity.setupActionObserver() {
    viewModel.updateBackground.observe(this) {
        val image = it
        if (image is Bitmap) {
            viewModel.clearLeaveData()
            dataBinding.backgroundView.setImageBitmap(image)
        }
    }
}

fun WeatherActivity.showSnackBar(message: String) {
    val rootView = window.decorView.rootView
    Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
}

fun WeatherActivity.changeCityBackground(city: String) {
    //updateCityBackground("https://media.nomadicmatt.com/2020/bergen2.jpg")
    //viewModel.updateCityBackground("https://media.nomadicmatt.com/2020/bergen2.jpg")

    viewModel.changeCityBackground(city)
}