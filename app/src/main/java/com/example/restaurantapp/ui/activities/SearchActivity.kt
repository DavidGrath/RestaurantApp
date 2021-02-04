package com.example.restaurantapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.Constants
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivitySearchBinding
import com.example.restaurantapp.domain.entities.ui.RestaurantUI
import com.example.restaurantapp.ui.adapters.EmptyAdapter
import com.example.restaurantapp.ui.adapters.ProgressAdapter
import com.example.restaurantapp.ui.adapters.RestaurantRecyclerAdapter
import com.example.restaurantapp.ui.viewmodels.SearchViewModel

class SearchActivity : AppCompatActivity(), RestaurantRecyclerAdapter.RestaurantItemClickListener {

    lateinit var binding : ActivitySearchBinding
    lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarSearch)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val locationId = preferences.getInt(Constants.PREFERENCES_LOCATION_ID, -1)
        val locationType = preferences.getInt(Constants.PREFERENCES_LOCATION_TYPE, -1)

        val emptyAdapter = EmptyAdapter("No Restaurants Found", "Please refine your search")
        val progressAdapter = ProgressAdapter()
        val errorAdapter = EmptyAdapter("An error occurred", "Please refresh")
        val restaurantRecyclerAdapter = RestaurantRecyclerAdapter(this)

        val restaurantsRecycler = binding.recyclerviewSearchRestaurants
        viewModel.restaurantsList.observe(this, {
            when(it) {
                is ApiResult.Success -> {
                    if(restaurantsRecycler.adapter !is RestaurantRecyclerAdapter) {
                        restaurantsRecycler.adapter = restaurantRecyclerAdapter
                    }
                    val restaurants = it.data!!
                    if(restaurants.isEmpty()) {
                        restaurantsRecycler.adapter = emptyAdapter
                    } else {
                        restaurantRecyclerAdapter.submitList(restaurants)
                    }
                }
                is ApiResult.Pending -> {
                    restaurantsRecycler.adapter = progressAdapter
                }
                is ApiResult.Failure -> {
                    restaurantsRecycler.adapter = errorAdapter
                }
            }
        })
        binding.searchviewRestaurants.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()) {
                    viewModel.searchRestaurants(locationId, locationType)
                    return true
                } else {
                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_search_filter -> {
                //TODO Replace with fragment transaction
                startActivity(Intent(this, FilterRestaurantSearchActivity::class.java))
                return true
            }
            else -> {
                return false
            }
        }
    }
    override fun onRestaurantClicked(position: Int, restaurant: RestaurantUI) {
        val bundle = Bundle().apply {
            putInt(Constants.RESTAURANT_DETAILS_ID, restaurant.id)
        }
        startActivity(Intent(this, RestaurantDetailsActivity::class.java), bundle)
    }
}