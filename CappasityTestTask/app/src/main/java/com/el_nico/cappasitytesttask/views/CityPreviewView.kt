package com.el_nico.cappasitytesttask.views

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.el_nico.cappasitytesttask.BuildConfig
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.databinding.LayoutCityPreviewBinding
import com.el_nico.cappasitytesttask.utils.GeocodingUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.util.*
import java.util.concurrent.Executors

@SuppressLint("ViewConstructor")
class CityPreviewView(latitude: Double, longitude: Double,
                      context: Context,
                      attrs: AttributeSet? = null,
                      defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr) {

    val dataBinding: LayoutCityPreviewBinding = LayoutCityPreviewBinding.bind(
        View.inflate(context, R.layout.layout_city_preview, this))

    init {
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID

        dataBinding.osmMap.setTileSource(TileSourceFactory.MAPNIK)
        dataBinding.osmMap.onResume()

        val geoPoint = GeoPoint(latitude, longitude)
        moveMapToSearchResults(geoPoint)
    }

    private fun moveMapToSearchResults(location: GeoPoint) {
        dataBinding.osmMap.controller.animateTo(location)
        dataBinding.osmMap.controller.setZoom(12.0)

        val marker = Marker(dataBinding.osmMap)
        marker.position = location
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        dataBinding.osmMap.overlays.add(marker)
    }
}