package com.example.travelapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

private const val API_KEY = "Jw38YYEmjErxiCWnBxc3H9w6JFWke2OlSSLgwO9KvSY"

class MainActivity : AppCompatActivity() {
    private var mCity: String = ""
    private var mCountry: String = ""
    private var cityNameList: MutableList<City> = mutableListOf()

    lateinit var addcityRV: RecyclerView
    private var citylistAdapter: CitylistAdapter = CitylistAdapter(cityNameList)
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addcityBT = findViewById<Button>(R.id.addcityBT)
        addcityRV = findViewById<RecyclerView>(R.id.addcityRV)
        val decoration = SpacesItemDecoration(16)
        addcityRV.addItemDecoration(decoration)

        addcityBT.setOnClickListener {
            startMapActivity()
        }

    }

    override fun onResume() {
        super.onResume()
        if(citylistAdapter.itemCount > 0) {
            val addcityTV = findViewById<TextView>(R.id.addcityTV)
            addcityTV.textSize = 36F
            addcityTV.text = "Your Cities"
        }
    }

    var editActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // If the user comes back to this activity from EditActivity
        // with no error or cancellation
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            // Get the data passed from EditActivity
            if (data != null) {
                var cityCountry = data.extras!!.getString("newString")
                if (cityCountry != null) {
                    val temp = cityCountry.split(",").toTypedArray()
                    mCity = temp[0]
                    mCountry = temp[1]
                    if(mCity == "null") {
                        cityCountry = mCountry
                    }
                    updateAdapter(cityCountry)
                }
            }
        }
    }

    fun startMapActivity() {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("stringToEdit", "CodePath")
        editActivityResultLauncher.launch(intent)
    }

    private fun updateAdapter(cityCountryString: String) {

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["client_id"] = API_KEY

        // Using the client, perform the HTTP request
        client[
                "https://api.unsplash.com/photos/random/?query=${cityCountryString} building&",
                params,
                object : JsonHttpResponseHandler()
                { //connect these callbacks to your API call
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        val urlsJSON : JSONObject = json.jsonObject.get("urls") as JSONObject
                        val urlJSON : String = urlsJSON.get("full").toString()
                        url = urlJSON
                        val city = City(mCity, mCountry, url)
                        cityNameList.add(city)
                        citylistAdapter = CitylistAdapter(cityNameList)
                        addcityRV.adapter = citylistAdapter
                        addcityRV.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MainActivity", errorResponse)
                        }
                    }
                }]
    }



}