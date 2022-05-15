package com.el_nico.cappasitytesttask.fragments.weatherdetails

import android.app.AlertDialog
import android.view.View
import androidx.navigation.fragment.findNavController
import com.el_nico.cappasitytesttask.R
import com.el_nico.cappasitytesttask.activities.WeatherActivity
import com.el_nico.cappasitytesttask.activities.showSnackBar

fun WeatherDetailsFragment.setupActionObserver() {
    viewModel.deleteSavedCity.observe(viewLifecycleOwner) {
        if (it) {
            viewModel.clearLeaveData()
            presentCityDeletionDialog()
        }
    }

    viewModel.presentLoadingIndicator.observe(viewLifecycleOwner) {
        dataBinding.nowLoading.visibility = if (it) View.VISIBLE else View.GONE
    }

    viewModel.presentMessage.observe(viewLifecycleOwner) {
        val message = it
        if (message is String) {
            viewModel.clearLeaveData()
            (activity as WeatherActivity).showSnackBar(message)
        }
    }

    viewModel.returnAfterDeletion.observe(viewLifecycleOwner) {
        if (it) {
            viewModel.clearLeaveData()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}

fun WeatherDetailsFragment.presentCityDeletionDialog() {
    val builder = AlertDialog.Builder(requireContext(), android.R.style.Theme_Material_Dialog_Alert)
    builder.setMessage(R.string.confirmation)
        .setPositiveButton(R.string.yes) { dialog, _ ->
            dialog.dismiss()
            deleteCityFromSavedAndReturn()
        }.setNegativeButton(R.string.no) { dialog, _ ->
            dialog.dismiss()
        }.setOnCancelListener { dialog ->
            dialog.dismiss()
        }.create()
    builder.show()
}

fun WeatherDetailsFragment.deleteCityFromSavedAndReturn() {
    val activity = activity as WeatherActivity
    val savedCityID = activity.savedCityID
    if (savedCityID is Int) {
        viewModel.deleteCityFromSavedAndReturn(savedCityID)
    }
}