package com.example.simplecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
 //val are constant variable like const, final..
 //var are regular variable

 var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Get the reference of button and TextView using their id
        val button = findViewById<Button>(R.id.Button)
        val textView = findViewById<TextView>(R.id.textView)
        //ALWAYS CREATE A REFERENCE OF A BUTTON
        val button1 = findViewById<Button>(R.id.Button1)

        button.setOnClickListener {
            //Display the text section when you click on the button
            //Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            //The textView is the section containing the text
            //Set the counter to be printed in the text section
            counter++
            textView.text = counter.toString()
            if (counter > 2) {
                button1.isVisible = true
                button1.setOnClickListener {
                    counter += 2
                    textView.text = counter.toString()
                }
            }
        }
    }
}