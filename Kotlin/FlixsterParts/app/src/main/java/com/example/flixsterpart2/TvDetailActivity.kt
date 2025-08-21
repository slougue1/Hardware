package com.example.flixsterpart2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TV_TAG = "MovieDetailActivity"

class TvDetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var rateTextView: TextView
    private lateinit var rateCountTextView: TextView
    private lateinit var releaseDateTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_tv)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        rateTextView = findViewById(R.id.mediaRate)
        rateCountTextView = findViewById(R.id.mediaRateCount)
        releaseDateTextView = findViewById(R.id.mediaReleaseDate)
        overviewTextView = findViewById(R.id.mediaOverview)

        // TODO: Get the extra from the Intent
        val tv = intent.getSerializableExtra(TV_EXTRA) as Tv

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = tv.title.toString()
        rateTextView.text = "Rate : " + tv.vote.toString()
        rateCountTextView.text = "Rate Count: " + tv.count.toString()
        releaseDateTextView.text = "First Air Date: " + tv.release_date
        overviewTextView.text = "Overview: " + tv.overview

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + tv.poster_path)
            .into(mediaImageView)


    }
}