package com.el_nico.cappasitytesttask.interfaces.components

import com.el_nico.cappasitytesttask.fragments.weatherdetails.WeatherDetailsFragment
import com.el_nico.cappasitytesttask.modules.WeatherDetailsModule
import dagger.Component

@Component(modules = [
    WeatherDetailsModule::class
])
interface WeatherDetailsComponent {
    fun inject(fragment: WeatherDetailsFragment)
}