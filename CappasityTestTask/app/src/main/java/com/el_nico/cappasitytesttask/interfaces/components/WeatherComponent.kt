package com.el_nico.cappasitytesttask.interfaces.components

import com.el_nico.cappasitytesttask.activities.WeatherActivity
import com.el_nico.cappasitytesttask.modules.WeatherModule
import dagger.Component

@Component(modules = [
    WeatherModule::class
])
interface WeatherComponent {
    fun inject(fragment: WeatherActivity)
}