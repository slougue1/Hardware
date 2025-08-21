package com.example.travelapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.withContext

class CitylistAdapter(private val citylists: List<City>) : RecyclerView.Adapter<CitylistAdapter.CityViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val cityitemcityTV: TextView
        val cityitemcountryTV: TextView
        val cityitemIV: ImageView


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            cityitemcityTV = itemView.findViewById(R.id.cityitemcityTV)
            cityitemcountryTV = itemView.findViewById(R.id.cityitemcountryTV)
            cityitemIV = itemView.findViewById(R.id.cityitemIV)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    inner class CityViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: City? = null
        val mCityItemIV : ImageView = mView.findViewById<View>(R.id.cityitemIV) as ImageView
        val mCityItemCityTV: TextView = mView.findViewById<View>(R.id.cityitemcityTV) as TextView
        val mCityItemCountryTV: TextView = mView.findViewById<View>(R.id.cityitemcountryTV) as TextView
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val citylist = citylists[position]
        // Set item views based on views and data model
        holder.mItem = citylist
        holder.mCityItemCityTV.text = citylist.cityName
        holder.mCityItemCountryTV.text = citylist.countryName
        Log.i("urle", "${citylist.cityURL}")

        Glide.with(holder.mView)
            .load(citylist.cityURL)
            .into(holder.mCityItemIV)
    }

    override fun getItemCount(): Int {
        return citylists.size
    }

}