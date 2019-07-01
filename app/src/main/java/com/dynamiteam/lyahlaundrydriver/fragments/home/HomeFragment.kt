package com.dynamiteam.lyahlaundrydriver.fragments.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.dynamiteam.lyahlaundrydriver.R
import com.dynamiteam.lyahlaundrydriver.activities.MainCallback
import com.dynamiteam.lyahlaundrydriver.base.BaseFragment
import com.dynamiteam.lyahlaundrydriver.tools.PrefManager
import com.dynamiteam.lyahlaundrydriver.tools.bindInterfaceOrThrow
import com.fondesa.kpermissions.extension.listeners
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : BaseFragment<HomeVM>(), LocationListener {

    val database = FirebaseDatabase.getInstance();

    private val request: PermissionRequest by lazy {
        permissionsBuilder(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ).build()
    }
    override val layoutId = R.layout.fr_home
    override val viewModelClass = HomeVM::class.java
    private lateinit var locationManager: LocationManager

    override fun observeLiveData() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        request.send()
        request.listeners {
            onAccepted {
                getLocation()
            }
            onDenied {
                callback?.showSnack("permission denied")
            }
            onPermanentlyDenied {
                callback?.showSnack("permission denied permanently")
            }
            onShouldShowRationale { strings, permissionNonce ->
                callback?.showSnack("blahh")
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        callback?.showSnack("WoooHHHH")
        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isNetworkEnabled && !isGpsEnabled) {
            callback?.showSnack("no gps and network connection")
        } else {
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 0f, this)
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0f, this)
            }
        }
    }

    var callback: MainCallback? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = bindInterfaceOrThrow<MainCallback>(parentFragment, context)
    }

    override fun onLocationChanged(location: Location?) {
        showSnack(location?.latitude.toString() + "\n" + location?.longitude)
        viewModel.updateLocation(PrefManager.userId.toInt(), location)
        var myRef = database.getReference("location")
        var driverRef = myRef.child("6")
        driverRef.setValue(location?.latitude?.let { sampleDb(it,location?.longitude) })
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }
}

data class sampleDb(
    var lat: Double,
    var lon: Double
)