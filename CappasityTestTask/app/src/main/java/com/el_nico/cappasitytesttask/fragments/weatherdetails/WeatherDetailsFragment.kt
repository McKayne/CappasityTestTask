package com.el_nico.cappasitytesttask.fragments.weatherdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.el_nico.cappasitytesttask.activities.WeatherActivity
import com.el_nico.cappasitytesttask.activities.changeCityBackground
import com.el_nico.cappasitytesttask.adapters.TabsAdapter
import com.el_nico.cappasitytesttask.databinding.FragmentWeatherDetailsBinding
import com.el_nico.cappasitytesttask.enums.WeatherDetailsType
import com.el_nico.cappasitytesttask.fragments.currentweather.CurrentWeatherFragment
import com.el_nico.cappasitytesttask.interfaces.components.DaggerWeatherDetailsComponent
import com.el_nico.cappasitytesttask.interfaces.components.WeatherDetailsComponent
import com.el_nico.cappasitytesttask.modules.WeatherDetailsModule
import com.el_nico.cappasitytesttask.utils.database.WeatherDatabase
import com.el_nico.cappasitytesttask.utils.database.infoForCityWithID
import com.el_nico.cappasitytesttask.viewmodels.WeatherDetailsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherDetailsFragment : Fragment() {

    private lateinit var component: WeatherDetailsComponent

    @Inject
    internal lateinit var dataBinding: FragmentWeatherDetailsBinding

    @Inject
    internal lateinit var viewModel: WeatherDetailsViewModel

    override fun onStart() {
        super.onStart()

        val activity = activity as WeatherActivity
        val savedCityID = activity.savedCityID
        if (savedCityID is Int) {
            WeatherDatabase.infoForCityWithID(savedCityID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<Triple<String, Double?, Double?>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(info: Triple<String, Double?, Double?>) {
                        val city = info.first
                        activity.dataBinding.toolbar.title = city
                        activity.changeCityBackground(city)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component = DaggerWeatherDetailsComponent.builder()
            .weatherDetailsModule(WeatherDetailsModule(inflater, container, this)).build()
        component.inject(this)

        dataBinding.weatherDetailsViewModel = viewModel
        dataBinding.lifecycleOwner = this

        setupActionObserver()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = CurrentWeatherFragment(WeatherDetailsType.WEATHER)
        val fragment2 = CurrentWeatherFragment(WeatherDetailsType.FORECAST)
        val fragment3 = CurrentWeatherFragment(WeatherDetailsType.ONECALL)

        val adapter = TabsAdapter(requireActivity())
        adapter.addFragment(fragment, "Погода сейчас")
        adapter.addFragment(fragment2, "Прогноз на 5 дней")
        adapter.addFragment(fragment3, "Прогноз на неделю")

        dataBinding.viewpager.adapter = adapter
        dataBinding.viewpager.currentItem = 0

        val tabLayout = dataBinding.tabLayout
        val viewPager = dataBinding.viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }
}