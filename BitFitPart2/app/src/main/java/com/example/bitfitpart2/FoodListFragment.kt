package com.example.bitfitpart2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodListFragment : Fragment() {
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val foods = mutableListOf<DisplayFood>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)

        foodsRecyclerView = view.findViewById(R.id.fragment_recycler_view)

        val foodAdapter = FoodAdapter(view.context, foods)
        foodsRecyclerView.adapter = foodAdapter

        lifecycle.launch {
            (activity?.application as FoodApplication).db.foodDAO().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name, entity.calories
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        foodsRecyclerView.layoutManager = LinearLayoutManager(view.context).also {
            val dividerItemDecoration = DividerItemDecoration(view.context, it.orientation)
            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        return view
    }
    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}