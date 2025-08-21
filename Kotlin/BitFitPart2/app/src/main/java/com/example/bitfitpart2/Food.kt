package com.example.bitfitpart2

import android.os.Parcelable

@Parcelize
data class Food1(
    val name: String? = "",
    val calories: Double?
) : Parcelable
