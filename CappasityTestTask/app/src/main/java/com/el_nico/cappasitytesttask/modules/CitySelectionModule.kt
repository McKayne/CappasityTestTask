package com.el_nico.cappasitytesttask.modules

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.adapters.SavedCitiesAdapter
import com.el_nico.cappasitytesttask.databinding.FragmentCitySelectionBinding
import com.el_nico.cappasitytesttask.viewmodels.CitySelectionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class CitySelectionModule(private val inflater: LayoutInflater,
                          private val container: ViewGroup?,
                          private val owner: ViewModelStoreOwner) {

    @Provides
    fun providesFragmentCitySelectionBinding() = DataBindingUtil.inflate(inflater,
        R.layout.fragment_city_selection, container, false) as FragmentCitySelectionBinding

    @Provides
    fun providesCitySelectionViewModel() = ViewModelProvider(owner)
        .get(CitySelectionViewModel::class.java)

    @Provides
    fun provideSavedCitiesAdapter() = SavedCitiesAdapter()
}