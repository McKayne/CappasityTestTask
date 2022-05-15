package com.el_nico.cappasitytesttask.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.databinding.ActivityWeatherBinding
import com.el_nico.cappasitytesttask.interfaces.components.DaggerWeatherComponent
import com.el_nico.cappasitytesttask.interfaces.components.WeatherComponent
import com.el_nico.cappasitytesttask.modules.WeatherModule
import com.el_nico.cappasitytesttask.viewmodels.WeatherViewModel
import javax.inject.Inject

class WeatherActivity: AppCompatActivity() {

    private lateinit var component: WeatherComponent

    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    internal lateinit var dataBinding: ActivityWeatherBinding

    @Inject
    internal lateinit var viewModel: WeatherViewModel

    internal var savedCityID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        component = DaggerWeatherComponent.builder()
            .weatherModule(WeatherModule(layoutInflater, this)).build()
        component.inject(this)

        dataBinding.weatherViewModel = viewModel
        dataBinding.lifecycleOwner = this
        setupActionObserver()

        setContentView(dataBinding.root)
        setSupportActionBar(dataBinding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}