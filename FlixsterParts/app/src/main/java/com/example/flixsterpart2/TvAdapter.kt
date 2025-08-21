package com.example.flixsterpart2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val TV_EXTRA = "MOVIE_EXTRA"
private const val TAG = "TvAdapter"

class TvAdapter(private val context: Context, private val tvs: List<Tv>) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val tv = tvs[position]
        holder.bind(tv)
    }

    override fun getItemCount() = tvs.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val rateTextView = itemView.findViewById<TextView>(R.id.mediaRate)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val tv = tvs[absoluteAdapterPosition]
            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, TvDetailActivity::class.java)
            intent.putExtra(TV_EXTRA, tv)
            context.startActivity(intent)
        }

        fun bind(tv: Tv) {
            titleTextView.text = tv.title.toString()
            rateTextView.text = "Rate: " + tv.vote.toString()

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + tv.poster_path)
                .into(mediaImageView)
        }
    }
}