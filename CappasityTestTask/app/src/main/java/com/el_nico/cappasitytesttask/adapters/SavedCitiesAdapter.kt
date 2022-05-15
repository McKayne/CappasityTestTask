package com.el_nico.cappasitytesttask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import com.el_nico.cappasitytesttask.viewholders.CityViewHolder
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.utils.CityDiffUtil

class SavedCitiesAdapter: ListAdapter<Int, CityViewHolder>(CityDiffUtil()) {

    internal var context: Context? = null

    internal var selectedIndex: MutableLiveData<Int> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        context = parent.context
        return CityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_saved_city, parent, false), this
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Если уже содержит значение то при возврате на соотв. фрагмент произойдет лишнее срабатывание
     * обсервера (и как следствие всех привязанных действий)
     */
    fun clearLeaveData() {
        selectedIndex.value = null
    }
}