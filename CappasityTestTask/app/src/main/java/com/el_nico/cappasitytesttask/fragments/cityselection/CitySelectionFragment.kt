package com.el_nico.cappasitytesttask.fragments.cityselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.el_nico.cappasitytesttask.activities.WeatherActivity
import com.el_nico.cappasitytesttask.activities.showSnackBar
import com.el_nico.cappasitytesttask.adapters.SavedCitiesAdapter
import com.el_nico.cappasitytesttask.application.WeatherApplication
import com.el_nico.cappasitytesttask.databinding.FragmentCitySelectionBinding
import com.el_nico.cappasitytesttask.interfaces.components.CitySelectionComponent
import com.el_nico.cappasitytesttask.interfaces.components.DaggerCitySelectionComponent
import com.el_nico.cappasitytesttask.modules.CitySelectionModule
import com.el_nico.cappasitytesttask.viewmodels.CitySelectionViewModel
import javax.inject.Inject

class CitySelectionFragment: Fragment() {

    private lateinit var component: CitySelectionComponent

    @Inject
    internal lateinit var dataBinding: FragmentCitySelectionBinding

    @Inject
    internal lateinit var viewModel: CitySelectionViewModel

    @Inject
    lateinit var adapter: SavedCitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component = DaggerCitySelectionComponent.builder()
            .citySelectionModule(CitySelectionModule(inflater, container, this)).build()
        component.inject(this)

        dataBinding.citySelectionViewModel = viewModel
        dataBinding.lifecycleOwner = this

        setupActionObserver()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = activity?.application as WeatherApplication
        application.dbInitialized.observe(viewLifecycleOwner) {
            dataBinding.nowLoading.visibility = View.GONE

            if (it) {
                setupList()
            } else {
                (activity as WeatherActivity).showSnackBar("Ошибка БД приложения")
            }
        }
    }
}