package com.example.bitfitpart1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity(

    @ColumnInfo(name = "foodName") val foodName: String?,
    @ColumnInfo(name = "caloriesNumber") val caloriesNumber: String?,
    @ColumnInfo(name = "caloriesText") val caloriesText: String?,
    @PrimaryKey(autoGenerate = true) val id: Long= 0,

    )