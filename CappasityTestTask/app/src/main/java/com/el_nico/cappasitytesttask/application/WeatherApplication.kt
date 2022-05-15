package com.el_nico.cappasitytesttask.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.utils.networking.Networking
import com.el_nico.cappasitytesttask.utils.database.WeatherDatabase
import com.el_nico.cappasitytesttask.utils.networking.ImageNetworking
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherApplication: Application() {

    private val isDatabaseInitialized = MutableLiveData<Boolean>()

    val dbInitialized: LiveData<Boolean> get() = isDatabaseInitialized

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Networking.init(this)
        ImageNetworking.init()

        WeatherDatabase.init(this)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: Boolean) {
                    isDatabaseInitialized.value = true
                }

                override fun onError(e: Throwable) {
                    isDatabaseInitialized.value = false
                }
            })
    }

    internal fun readConfigFile(): String {
        val inputStream = resources.openRawResource(R.raw.config)
        return inputStream.bufferedReader().use { it.readText() }
    }
}