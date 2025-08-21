package com.example.travelapp

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var cityNameList: MutableList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val mUiSettings = mMap.uiSettings



        // Keep the UI Settings state in sync with the checkboxes.
        mUiSettings.isZoomControlsEnabled = true
        mUiSettings.isCompassEnabled = true
        mUiSettings.isMyLocationButtonEnabled = true
        mUiSettings.isScrollGesturesEnabled = true
        mUiSettings.isZoomGesturesEnabled = true
        mUiSettings.isTiltGesturesEnabled = true
        mUiSettings.isRotateGesturesEnabled = true
        mUiSettings.isIndoorLevelPickerEnabled = true
        mUiSettings.isMyLocationButtonEnabled = true

        mMap.setOnMapLongClickListener(this)




    }

    override fun onMapLongClick(point: LatLng) {
        val mapsTV = findViewById<TextView>(R.id.mapsTV)
        val mapsBT = findViewById<Button>(R.id.mapsBT)

        mMap.clear()
        val markerOptions = MarkerOptions()
        markerOptions.position(point)
        mMap.addMarker(markerOptions)
        val geocoder = Geocoder(this)
        val address = geocoder.getFromLocation(point.latitude, point.longitude, 1)

        val mAddress = address[0].getAddressLine(0)
        Log.i("HERE", mAddress)
        mapsTV.setText(mAddress)

        mapsBT.setOnClickListener {
            //Log.i("WHERE", address[0].locality)
            val cityName = address[0].locality
            val countryName = address[0].countryName
            val cityCountry = cityName + "," + countryName
            onSubmit(cityCountry)
            //val instanceCity = City(cityName, countryName, "")
            //cityNameList.add(instanceCity)
        }

    }

    fun onSubmit(cityCountry: String) {
        // Prepare data intent
        val data = Intent()
        // Pass relevant data back as a result
        data.putExtra("newString", cityCountry)
        // Activity finished ok, return the data
        setResult(RESULT_OK, data) // set result code and bundle data for response
        finish() // closes the activity, pass data to parent
    }


}