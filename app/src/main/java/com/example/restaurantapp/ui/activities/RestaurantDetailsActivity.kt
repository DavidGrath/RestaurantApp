package com.example.restaurantapp.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.Constants
import com.example.restaurantapp.databinding.ActivityRestaurantDetailsBinding

class RestaurantDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityRestaurantDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarRestaurantDetails)

        val bundle = intent.extras!!
        val restaurantId = bundle.getInt(Constants.RESTAURANT_DETAILS_ID, -1)
        val message =
                if(restaurantId == -1) "Somehow, no restaurant ID, unit (or integration) test this?"
                else "Restaurant ID: $restaurantId"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}