package com.el_nico.cappasitytesttask.modules

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.databinding.FragmentWeatherDetailsBinding
import com.el_nico.cappasitytesttask.viewmodels.WeatherDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class WeatherDetailsModule(private val inflater: LayoutInflater,
                           private val container: ViewGroup?,
                           private val owner: ViewModelStoreOwner) {

    @Provides
    fun providesFragmentWeatherDetailsBinding() = DataBindingUtil.inflate(inflater,
        R.layout.fragment_weather_details, container, false) as FragmentWeatherDetailsBinding

    @Provides
    fun providesWeatherDetailsViewModel() = ViewModelProvider(owner)
        .get(WeatherDetailsViewModel::class.java)
}