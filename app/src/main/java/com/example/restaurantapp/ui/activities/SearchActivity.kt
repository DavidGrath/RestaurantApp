package com.example.restaurantapp.ui.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.Constants
import com.example.restaurantapp.Constants.Companion.LOCATION_MAP
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivitySearchBinding
import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions
import com.example.restaurantapp.domain.entities.ui.RestaurantUI
import com.example.restaurantapp.ui.SearchService
import com.example.restaurantapp.ui.adapters.EmptyAdapter
import com.example.restaurantapp.ui.adapters.ProgressAdapter
import com.example.restaurantapp.ui.adapters.RestaurantRecyclerAdapter
import com.example.restaurantapp.ui.viewmodels.SearchViewModel
import com.example.restaurantapp.ui.viewmodels.factories.SearchViewModelFactory

class SearchActivity : AppCompatActivity(), RestaurantRecyclerAdapter.RestaurantItemClickListener {

    lateinit var binding : ActivitySearchBinding
    lateinit var viewModel : SearchViewModel

    var locationId : Int? = null
    var locationType : String? = null

    val servConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SearchService.SearchBinder
            val defaultOptions = RestaurantSearchFilterOptions(locationId,
                    LOCATION_MAP.get(locationType)?: Constants.Companion.LocationType.CITY.locationType)
            viewModel = ViewModelProvider(this@SearchActivity,
                    SearchViewModelFactory(binder, defaultOptions)).get(SearchViewModel::class.java)
            viewModel.setOptions(defaultOptions)
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarSearch)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        bindService(Intent(this, SearchService::class.java), servConn, Context.BIND_AUTO_CREATE)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        locationId = preferences.getInt(Constants.PREFERENCES_LOCATION_ID, -1)
        locationType = preferences.getString(Constants.PREFERENCES_LOCATION_TYPE, Constants.Companion.LocationType.CITY.locationType)

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
                    viewModel.getRestaurants()
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