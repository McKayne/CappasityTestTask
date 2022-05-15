package com.el_nico.cappasitytesttask.interfaces.components

import com.el_nico.cappasitytesttask.fragments.cityselection.CitySelectionFragment
import com.el_nico.cappasitytesttask.modules.CitySelectionModule
import dagger.Component

@Component(modules = [
    CitySelectionModule::class
])
interface CitySelectionComponent {
    fun inject(fragment: CitySelectionFragment)
}