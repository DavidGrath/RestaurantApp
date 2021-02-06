package com.example.restaurantapp.ui.activities

import android.app.Dialog
import android.content.*
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.restaurantapp.ApiResult
import com.example.restaurantapp.Constants
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityMainBinding
import com.example.restaurantapp.domain.entities.ui.LocationUI
import com.example.restaurantapp.ui.adapters.EmptyAdapter
import com.example.restaurantapp.ui.adapters.LocationSearchRecyclerAdapter
import com.example.restaurantapp.ui.adapters.ProgressAdapter
import com.example.restaurantapp.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() ,LocationSearchRecyclerAdapter.SearchItemClickListener{

    lateinit var viewModel : MainViewModel
    lateinit var binding : ActivityMainBinding
    lateinit var preferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val location = preferences.getInt(Constants.PREFERENCES_LOCATION_ID, -1)
        if(location != -1) {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        val locationResults = binding.recyclerviewMainSearch

        val searchAdapter = LocationSearchRecyclerAdapter(this)
        val emptyAdapter = EmptyAdapter("No Results", "Please enter a different search term")
        val progressAdapter = ProgressAdapter()

        viewModel.searchResult.observe(this@MainActivity, {
            when(it) {
                is ApiResult.Success -> {
                    val data = it.data!!
                    if(data.isEmpty()) {
                        locationResults.adapter = emptyAdapter
                    } else {
                        if(locationResults.adapter !is LocationSearchRecyclerAdapter) {
                            locationResults.adapter = searchAdapter
                        }
                        searchAdapter.submitList(data)
                    }
                }
                is ApiResult.Pending -> {
                    locationResults.adapter = progressAdapter
                }
                is ApiResult.Failure -> {
                    locationResults.adapter = EmptyAdapter("Error", "Please try again later")
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search_main)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()) {
                    viewModel.searchLocations(query)
                    return true
                } else {
                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onLocationClick(position: Int, item: LocationUI) {
        val CONFIRM_LOCATION_DIALOG_TAG = "confirm_location"
        val listener = DialogInterface.OnClickListener { dialog, which ->
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    finishLocation(item)
                    dialog.dismiss()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    dialog.dismiss()
                }
            }
        }
        val locationDialog : DialogFragment = object : DialogFragment() {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                return AlertDialog.Builder(requireContext())
                        .setMessage("Set ${item.name} as location?")
                        .setPositiveButton("Yes", listener)
                        .setNegativeButton("No", listener)
                        .create()
            }
        }
        locationDialog.show(supportFragmentManager, CONFIRM_LOCATION_DIALOG_TAG)
        setLocation(item)
    }
    fun setLocation(item : LocationUI) {
        val editor = preferences.edit()
        editor.putInt(Constants.PREFERENCES_LOCATION_ID, item.id)
        editor.putString(Constants.PREFERENCES_LOCATION_NAME, item.name)
        editor.putString(Constants.PREFERENCES_LOCATION_TYPE, item.type)
        editor.apply()
    }
    fun finishLocation(item: LocationUI) {
        setLocation(item)
        Toast.makeText(this, "Location set to ${item.name}. Change in settings", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, SearchActivity::class.java))
        finish()
    }
}
