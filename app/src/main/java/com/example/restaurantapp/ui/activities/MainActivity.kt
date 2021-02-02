package com.example.restaurantapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.Constants
import com.example.restaurantapp.R
import com.example.restaurantapp.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() , View.OnClickListener{

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val location = preferences.getInt(Constants.PREFERENCES_LOCATION, -1)
        if(location != -1) {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        viewModel.searchResult.observe(this@MainActivity, Observer {
            when(it) {
                is ApiResult.Success -> {

                }
                is ApiResult.Pending -> {

                }
                is ApiResult.Failure -> {

                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search_main)
        val searchView = searchItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchLocations(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(v: View?) {

    }
}
