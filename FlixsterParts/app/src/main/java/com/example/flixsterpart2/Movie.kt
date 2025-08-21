package com.example.flixsterpart2

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class BaseResponse(
    @SerialName("results")
    val result: List<Movie>?
)

@Keep
@Serializable
data class Movie(
    @SerialName("name")
    val title: String?,
    @SerialName("formatted_address")
    val vote: String?,
    @SerialName("website")
    val count: String?,
    @SerialName("rating")
    val poster_path: String?,
    @SerialName("release_date")
    val release_date: String?,
    @SerialName("opening_hours")
    val overview: String?

) : java.io.Serializable
