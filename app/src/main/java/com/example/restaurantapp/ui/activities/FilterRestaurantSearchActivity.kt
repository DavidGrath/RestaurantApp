package com.example.restaurantapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.databinding.FragmentFilterRestaurantSearchBinding


//TODO Change into a fragment in SearchActivity
class FilterRestaurantSearchActivity : AppCompatActivity(){

    lateinit var binding : FragmentFilterRestaurantSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFilterRestaurantSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarFilterRestaurantsTemp)
    }
}