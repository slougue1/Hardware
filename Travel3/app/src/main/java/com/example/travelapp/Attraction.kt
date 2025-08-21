package com.example.travelapp

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val MAPS_API_KEY = "AIzaSyC_aC5Tt6JJiBbPxyMwbf2WCcX6_iisFU8"

@Keep
@Serializable
data class SearchNearbyResponse(
    @SerialName("results")
    val docs: List<Place>?
)

@Keep
@Serializable
data class Place(
    @SerialName("name")
    val attrName: String?,
    @SerialName("formatted_address")
    val attrAddress: String?,
    @SerialName("website")
    val attrWebsite: String?,
    @SerialName("international_phone_number")
    val attrIntPhone: String?,
    @SerialName("rating")
    val attrRating: Float,
    @SerialName("photos")
    val attrPic: List<PlacePhoto>?,
    @SerialName("opening_hours")
    val attrOpenHours: PlaceOpeningHours?,
) : java.io.Serializable {
    val mediaImageUrl =
        "https://maps.googleapis.com/maps/api/place/photo?${attrPic}&key=${MAPS_API_KEY}"
}

@Keep
@Serializable
data class PlacePhoto(
    @SerialName("photo_reference")
    val multimedia: String
) : java.io.Serializable

@Keep
@Serializable
data class PlaceOpeningHours(
    @SerialName("open_now")
    val openNow: Boolean?
) : java.io.Serializable



