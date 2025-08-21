package com.example.bitfitpart2

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers.IO

class DetailActivity : AppCompatActivity() {
    private lateinit var foodTextView: TextView
    private lateinit var caloriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        foodTextView = findViewById(R.id.food_ET)
        caloriesTextView = findViewById(R.id.calories_ET)

        findViewById<Button>(R.id.submit_BT).setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.foodDAO().insert(
                    FoodEntity(
                        name = foodTextView.text.toString(),
                        calories = caloriesTextView.text.toString().toDouble()
                    )
                )
            }
            foodTextView.hideKeyboard()
            foodTextView.text = ""
            caloriesTextView.text = ""
        }

    }
    fun View.hideKeyboard() {
        val hide = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(windowToken, 0)
    }
}