package com.example.bitfitpart2

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.google.android.gms.home.matter.commissioning.Room

@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDAO(): FoodDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Food-db"
            ).build()

    }
}
