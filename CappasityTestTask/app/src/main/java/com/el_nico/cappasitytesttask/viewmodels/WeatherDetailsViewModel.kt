package com.el_nico.cappasitytesttask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.el_nico.cappasitytesttask.utils.database.WeatherDatabase
import com.el_nico.cappasitytesttask.utils.database.deleteSavedCity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherDetailsViewModel: ViewModel() {

    private val shouldDeleteSavedCity = MutableLiveData<Boolean>()

    private val shouldPresentLoadingIndicator = MutableLiveData<Boolean>()

    private val shouldPresentMessage = MutableLiveData<String?>()

    private val shouldReturnAfterDeletion = MutableLiveData<Boolean>()

    val deleteSavedCity: LiveData<Boolean> get() = shouldDeleteSavedCity

    val presentLoadingIndicator: LiveData<Boolean> get() = shouldPresentLoadingIndicator

    val presentMessage: LiveData<String?> get() = shouldPresentMessage

    val returnAfterDeletion: LiveData<Boolean> get() = shouldReturnAfterDeletion

    fun deleteCityFromSavedAndReturn(id: Int) {
        WeatherDatabase.deleteSavedCity(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable) {
                    shouldPresentLoadingIndicator.value = true
                }

                override fun onSuccess(t: Boolean) {
                    shouldPresentLoadingIndicator.value = false
                    shouldReturnAfterDeletion.value = true
                }

                override fun onError(e: Throwable) {
                    shouldPresentLoadingIndicator.value = false
                    shouldPresentMessage.value = e.localizedMessage ?: e.stackTraceToString()
                }
            })
    }

    fun presentCityDeletionDialog() {
        shouldDeleteSavedCity.value = true
    }

    /**
     * Если уже содержит значение то при возврате на соотв. фрагмент произойдет лишнее срабатывание
     * обсервера (и как следствие всех привязанных действий)
     */
    fun clearLeaveData() {
        shouldDeleteSavedCity.value = false
        shouldPresentMessage.value = null
        shouldReturnAfterDeletion.value = false
    }
}