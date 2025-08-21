package com.example.bitfitpart1

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<FoodEntity>>
    @Insert
    fun insertAll(food: List<FoodEntity>)
    @Insert
    fun insert(food: FoodEntity)
    @Query("DELETE FROM food_table")
    fun removeAll()
    @Delete
    fun delete(food: FoodEntity)

}