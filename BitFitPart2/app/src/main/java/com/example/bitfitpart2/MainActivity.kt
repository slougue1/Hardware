package com.example.bitfitpart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Math.log
import java.lang.StrictMath.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        replaceFragment(FoodListFragment())

        val foodListFragment: Fragment = FoodListFragment()
        val dashboardFragment: Fragment = DashboardFragment()

        val bottomNavigationBarView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationBarView.setOnItemSelectedListener { item ->
            lateinit var thisFragment: Fragment
            when (item.itemId) {
                R.id.log -> thisFragment = foodListFragment
                R.id.dashboard -> thisFragment = dashboardFragment
            }
            replaceFragment(thisFragment)
            true
        }

        bottomNavigationBarView.selectedItemId = R.id.log

        findViewById<Button>(R.id.addNew_BT).setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
    private fun replaceFragment(thisFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_try, thisFragment)
        fragmentTransaction.commit()
    }
}


