package com.example.citysearchapp.ui.citydetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.citysearchapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 * Fragment shows the coordinate of city over google maps
 */

class CityDetailFragment : SupportMapFragment(), OnMapReadyCallback {

    // Navigation
    private val args: CityDetailFragmentArgs by navArgs()

    private val cityZoom = 12f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
    }

    override fun onMapReady(googleMaps: GoogleMap) {
        googleMaps.apply {
            addMarker(
                MarkerOptions()
                    .position(LatLng(args.coordinate.lat, args.coordinate.lon))
                    .title(
                        this@CityDetailFragment.getString(
                            R.string.map_marker_title,
                            args.cityName
                        )
                    )
            )
            moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(args.coordinate.lat, args.coordinate.lon),
                    cityZoom
                )
            )
        }
    }
}