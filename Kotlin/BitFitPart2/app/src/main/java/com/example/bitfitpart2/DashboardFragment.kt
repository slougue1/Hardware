package com.example.bitfitpart2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers

class DashboardFragment : Fragment() {
    private val foods = mutableListOf<DisplayFood>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val averageCalories = view.findViewById<TextView>(R.id.average_caloriesTV)
        val minCalories = view.findViewById<TextView>(R.id.minimum_calorieTV)
        val maxCalories = view.findViewById<TextView>(R.id.maximum_calorieTV)
        val clearDataButton = view.findViewById<Button>(R.id.clear_dataBT)
        val foodAdapter = FoodAdapter(view.context, foods)
        var minCal = 0
        var maxCal = 0
        var avgCal = 0
        var totalCalories = 0
        averageCalories.text = avgCal.toString()
        minCalories.text = minCal.toString()
        maxCalories.text = maxCal.toString()

        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.foodDAO().getAll()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        DisplayFood(
                            entity.name, entity.calories
                        )
                    }.also { mappedList ->
                        foods.clear()
                        foods.addAll(mappedList)
                        if (foods.size > 0) {
                            minCal = foods.get(0).calories!!.toInt()
                            for (i in foods) {
                                if (i.calories!! > maxCal) {
                                    maxCal = i.calories.toInt()
                                } else if (i.calories < minCal) {
                                    minCal = i.calories.toInt()
                                }
                                totalCalories += i.calories.toInt()
                            }
                            maxCalories.text = maxCal.toString()
                            minCalories.text = minCal.toString()
                            avgCal = totalCalories / foods.size
                            averageCalories.text = avgCal.toString()
                            foodAdapter.notifyDataSetChanged()
                        }

                    }
                }
        }
        clearDataButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (activity?.application as FoodApplication).db.foodDAO().deleteAll()
            }
        }
        return view
    }
}
