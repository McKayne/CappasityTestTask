package com.el_nico.cappasitytesttask.modules

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.databinding.FragmentCurrentWeatherBinding
import com.el_nico.cappasitytesttask.viewmodels.CurrentWeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class CurrentWeatherModule(private val inflater: LayoutInflater,
                           private val container: ViewGroup?,
                           private val owner: ViewModelStoreOwner) {

    @Provides
    fun providesFragmentCurrentWeatherBinding() = DataBindingUtil.inflate(inflater,
        R.layout.fragment_current_weather, container, false) as FragmentCurrentWeatherBinding

    @Provides
    fun providesCurrentWeatherViewModel() = ViewModelProvider(owner)
        .get(CurrentWeatherViewModel::class.java)
}