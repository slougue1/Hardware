package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val ATTR_EXTRA = "ATTR_EXTRA"
class AttrListAdapter (private val context: Context, private val attractions: List<Place>) : RecyclerView.Adapter<AttrListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.attr_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attraction = attractions[position]
        holder.bind(attraction)
    }

    override fun getItemCount() = attractions.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val attrNameTextView = itemView.findViewById<TextView>(R.id.attrNameTV)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(attraction: Place) {
            attrNameTextView.text = attraction.attrName
        }

        override fun onClick(v: View?) {
            // Get selected attraction
            val attr = attractions[adapterPosition]

            //  Navigate to Attraction Details screen and pass selected attraction
            val intent = Intent(context, AttrDetailActivity::class.java)
            intent.putExtra(ATTR_EXTRA, attr)
            context.startActivity(intent)
        }
    }

}