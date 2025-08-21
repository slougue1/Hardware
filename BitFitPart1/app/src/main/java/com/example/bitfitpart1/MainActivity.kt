package com.example.bitfitpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val foods = mutableListOf<DisplayFood>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        foodRecyclerView = findViewById(R.id.foodListRV)

        val foodAdapter = FoodAdapter(this,foods)
        foodRecyclerView.adapter = foodAdapter

        lifecycleScope.launch {
            (application as FoodApplication).db.FoodDao().getAll().collect{ databaseList ->
                databaseList.map {entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.caloriesNumber,
                        entity.caloriesText
                    )
                }.also { mappedList ->
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }
        foodRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }
       val addButton = findViewById<Button>(R.id.addNewFood)
        addButton.setOnClickListener{
            val intent = Intent(this@MainActivity,DetailsActivity::class.java)

            startActivity(intent)
        }
        val deleteAll = findViewById<Button>(R.id.deleteAllBtn)
        deleteAll.setOnClickListener {
            val intent2 = Intent(this@MainActivity,ConfirmDeleteActivity::class.java)
            startActivity(intent2)
        } }
    }
