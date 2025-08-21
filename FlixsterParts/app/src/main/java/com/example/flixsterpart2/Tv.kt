

package com.example.flixsterpart2

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class TvBaseResponse(
    @SerialName("results")
    val result: List<Tv>?
)

@Keep
@Serializable
data class Tv(
    @SerialName("name")
    val title: String?,
    @SerialName("formatted_address")
    val vote: Double?,
    @SerialName("website")
    val count: Int?,
    @SerialName("international_phone_number")
    val poster_path: String?,
    @SerialName("rating")
    val release_date: String?,
    @SerialName("overview")
    val overview: String?

) : java.io.Serializable
