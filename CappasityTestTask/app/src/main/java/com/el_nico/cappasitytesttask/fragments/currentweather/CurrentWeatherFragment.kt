package com.el_nico.cappasitytesttask.fragments.currentweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.el_nico.cappasitytesttask.activities.WeatherActivity
import com.el_nico.cappasitytesttask.databinding.FragmentCurrentWeatherBinding
import com.el_nico.cappasitytesttask.enums.WeatherDetailsType
import com.el_nico.cappasitytesttask.interfaces.components.CurrentWeatherComponent
import com.el_nico.cappasitytesttask.interfaces.components.DaggerCurrentWeatherComponent
import com.el_nico.cappasitytesttask.modules.CurrentWeatherModule
import com.el_nico.cappasitytesttask.viewmodels.CurrentWeatherViewModel
import javax.inject.Inject

class CurrentWeatherFragment(private val type: WeatherDetailsType): Fragment() {

    private lateinit var component: CurrentWeatherComponent

    @Inject
    internal lateinit var dataBinding: FragmentCurrentWeatherBinding

    @Inject
    internal lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component = DaggerCurrentWeatherComponent.builder()
            .currentWeatherModule(CurrentWeatherModule(inflater, container, this)).build()
        component.inject(this)

        dataBinding.currentWeatherViewModel = viewModel
        dataBinding.lifecycleOwner = this
        viewModel.type = type

        setupActionObserver()
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()

        val activity = activity as WeatherActivity
        val savedCityID = activity.savedCityID
        if (savedCityID is Int) {
            dataBinding.weatherLayout.visibility = View.GONE
            dataBinding.forecastLayout.visibility = View.GONE
            dataBinding.oneCallLayout.visibility = View.GONE
            when (type) {
                WeatherDetailsType.WEATHER -> {
                    dataBinding.weatherLayout.visibility = View.VISIBLE
                    viewModel.presentWeatherFromDatabase(savedCityID, true)
                }
                WeatherDetailsType.FORECAST -> {
                    dataBinding.forecastLayout.visibility = View.VISIBLE
                    viewModel.presentForecastFromDatabase(savedCityID, true)
                }
                WeatherDetailsType.ONECALL -> {
                    dataBinding.oneCallLayout.visibility = View.VISIBLE
                    viewModel.presentOneCallFromDatabase(savedCityID, true)
                }
            }
        }
    }
}