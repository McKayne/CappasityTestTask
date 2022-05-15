package com.el_nico.cappasitytesttask.interfaces.components

import com.el_nico.cappasitytesttask.fragments.currentweather.CurrentWeatherFragment
import com.el_nico.cappasitytesttask.modules.CurrentWeatherModule
import dagger.Component

@Component(modules = [
    CurrentWeatherModule::class
])
interface CurrentWeatherComponent {
    fun inject(fragment: CurrentWeatherFragment)
}