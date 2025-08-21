package com.example.travelapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class AttrDetailActivity : AppCompatActivity() {
    private lateinit var attrImageView: ImageView
    private lateinit var attrNameTextView: TextView
    private lateinit var attrAddressTextView: TextView
    private lateinit var attrWebsiteTextView: TextView
    private lateinit var attrHoursTextView: TextView
    private lateinit var attrPhoneTextView: TextView
    private lateinit var attrRatingBar : RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attr_detail)

        attrImageView = findViewById(R.id.attrPicIV)
        attrNameTextView = findViewById(R.id.attrNameTV)
        attrAddressTextView = findViewById(R.id.attrAddressTV)
        attrWebsiteTextView = findViewById(R.id.attrWebTV)
        attrHoursTextView = findViewById(R.id.attrHoursInfoTV)
        attrPhoneTextView = findViewById(R.id.attrPhoneTV)
        attrRatingBar = findViewById(R.id.attrRatingBar)

        val attraction = intent.getSerializableExtra(ATTR_EXTRA) as Place

        // Set information for attraction details
        attrNameTextView.text = attraction.attrName
        attrAddressTextView.text = attraction.attrAddress
        attrWebsiteTextView.text = attraction.attrWebsite
        attrPhoneTextView.text = attraction.attrIntPhone

        // Load the media image
        Glide.with(this)
            .load(attraction.mediaImageUrl)
            .into(attrImageView)
    }
}