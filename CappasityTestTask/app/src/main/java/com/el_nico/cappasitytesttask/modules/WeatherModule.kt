package com.el_nico.cappasitytesttask.modules

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.el_nico.cappasitytesttask.databinding.ActivityWeatherBinding
import com.el_nico.cappasitytesttask.viewmodels.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class WeatherModule(private val layoutInflater: LayoutInflater,
                    private val owner: ViewModelStoreOwner) {

    @Provides
    fun providesActivityWeatherBinding() = ActivityWeatherBinding.inflate(layoutInflater)

    @Provides
    fun providesWeatherViewModel() = ViewModelProvider(owner).get(WeatherViewModel::class.java)
}