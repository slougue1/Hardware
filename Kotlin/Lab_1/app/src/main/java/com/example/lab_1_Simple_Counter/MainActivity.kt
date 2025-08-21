package com.example.lab_1_Simple_Counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lab_1.Rclass MainActivity : AppCompatActivity() {
//Var are non-fixed variable
//Val are constance, like final, const

    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Reference of button gotten by id
        val button = findViewById<Button>(R.id.button)
        //Reference of textView gotten by id
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            //Display a toast
            Toast.makeText(it.context,"Clicked Button!", Toast.LENGTH_SHORT).show()
            //counter++
            //textView.text = counter.toString()
        }
    }
}