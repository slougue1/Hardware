package com.example.bitfitpart1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val FOOD_EXTRA = "FOOD_EXTRA"
private const val TAG = "FoodAdapter"

class FoodAdapter(private val context: Context, private val foods: List<DisplayFood>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val foodNameTextView = itemView.findViewById<TextView>(R.id.foodNameTextView)
        private val numerofCalories = itemView.findViewById<TextView>(R.id.numberOfCalories)
        private val caloriesStringTextView = itemView.findViewById<TextView>(R.id.calories)
        val deleteButton = itemView.findViewById<Button>(R.id.deleteBtn)
        val comfirnDeletion = itemView.findViewById<Button>(R.id.confirmDelete)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {

            deleteButton.visibility = View.VISIBLE
            val food = foods[absoluteAdapterPosition]
       }
        fun bind(food: DisplayFood) {

            foodNameTextView.text = food.foodName
            numerofCalories.text = food.numOfCalories
        }
    }
}
