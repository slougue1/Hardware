package com.example.bitfitpart1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FoodEntity::class], version = 1)
abstract class appDataBase: RoomDatabase() {
    abstract fun FoodDao(): FoodDao

    companion object{
        @Volatile
        private var INSTANCE: appDataBase? = null

        fun getInstance(context: Context): appDataBase = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                appDataBase::class.java,"Food-Database"
            ).build()
    }
}