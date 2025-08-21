package com.example.travelapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "AttrListFragment"
private const val MAPS_API_KEY = "AIzaSyC_aC5Tt6JJiBbPxyMwbf2WCcX6_iisFU8"
private const val LATITUDE = 33.8670522
private const val LONGITUDE = 151.1957362
private const val ATTR_SEARCH_URL =
"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${LATITUDE}%2C${LONGITUDE}&radius=1500&type=shopping_mall&key=${MAPS_API_KEY}"

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}
class AttrListFragment : Fragment() {

    private val attractions = mutableListOf<Place>()
    private lateinit var attractionsRecyclerView: RecyclerView
    private lateinit var attractionAdapter: AttrListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_attr_list, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        attractionsRecyclerView = view.findViewById(R.id.attrListRV)
        attractionsRecyclerView.layoutManager = layoutManager
        attractionsRecyclerView.setHasFixedSize(true)
        attractionAdapter = AttrListAdapter(view.context, attractions)
        attractionsRecyclerView.adapter = attractionAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

    companion object {

        fun newInstance() : AttrListFragment {
            return AttrListFragment()
        }
    }

    private fun fetchAttractions() {
        val client = AsyncHttpClient()
        client.get(ATTR_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch attractions: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched attractions: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        SearchNearbyResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.response?.docs?.let { list ->
                        attractions.addAll(list)
                        attractionAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchAttractions()
    }
}