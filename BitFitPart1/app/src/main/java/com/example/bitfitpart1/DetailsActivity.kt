package com.example.bitfitpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers.IO

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val saveFoodButton = findViewById<Button>(R.id.SaveFoodBtn)
                saveFoodButton.setOnClickListener {
            val FoodNameEditText = findViewById<EditText>(R.id.foodNameEditText).text.toString()
            val caloriesNumberEditText = findViewById<EditText>(R.id.caloriesEditText).text.toString()
            val caloriesStringTExtView = findViewById<TextView>(R.id.CaloriesTextViewD).text.toString()

            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.FoodDao().insert(
                    FoodEntity(FoodNameEditText, caloriesNumberEditText,caloriesStringTExtView)
                )
            }

            val intent = Intent(this@DetailsActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}