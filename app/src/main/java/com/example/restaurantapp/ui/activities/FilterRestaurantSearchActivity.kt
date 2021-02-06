package com.example.restaurantapp.ui.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.CommaSepList
import com.example.restaurantapp.Constants
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentFilterRestaurantSearchBinding
import com.example.restaurantapp.domain.entities.RestaurantSearchFilterOptions
import com.example.restaurantapp.ui.SearchService
import com.example.restaurantapp.ui.viewmodels.SearchFilterViewModel
import com.example.restaurantapp.ui.viewmodels.factories.SearchFilterViewModelFactory


//TODO Change into a fragment in SearchActivity
class FilterRestaurantSearchActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : FragmentFilterRestaurantSearchBinding
    lateinit var viewModel : SearchFilterViewModel

    //TODO Concerning these ServiceConnection objects, I'm considering adding LeakCanary
    // which also makes me wonder how to remove it from the final build, but let that time come
    val servConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SearchService.SearchBinder
            viewModel = ViewModelProvider(this@FilterRestaurantSearchActivity,
                    SearchFilterViewModelFactory(binder)).get(SearchFilterViewModel::class.java)
            initForm(viewModel.getOptions())
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFilterRestaurantSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarFilterRestaurantsTemp)

        binding.buttonApplyFilter.setOnClickListener(this)
        bindService(Intent(this, SearchService::class.java), servConn, Context.BIND_AUTO_CREATE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_filter_clear -> {
                with(binding) {
                    textinputStartIndex.setText("")
                    textinputMaxCount.setText("")
                    textinputLatitude.setText("")
                    textinputLongitude.setText("")
                    textinputRadius.setText("")
                    textinputCuisines.setText("")
                    textinputEstablishmentType.setText("")
                    textinputCategory.setText("")
                    textinputCollectionId.setText("")
                    spinnerSortType.setSelection(0, true)
                    spinnerOrderType.setSelection(0, true)
                }
                viewModel.clearOptions()
                return true
            }
            else -> {
                return false
            }
        }
    }
    fun initForm(options: RestaurantSearchFilterOptions) {
        with(binding) {
            textinputStartIndex.setText(options.startOffset?.toString()?:"")
            textinputMaxCount.setText(options.count?.toString()?:"")
            textinputLatitude.setText(options.latitude?.toString()?:"")
            textinputLongitude.setText(options.longitude?.toString()?:"")
            textinputRadius.setText(options.radius?.toString()?:"")
            textinputCuisines.setText(options.cuisines?.toString()?:"")
            textinputEstablishmentType.setText(options.establishmentType?:"")
            textinputCategory.setText(options.category?:"")
            textinputCollectionId.setText(options.collectionID?:"")
            spinnerSortType.setSelection(options.sortBy?.ordinal?.plus(1) ?:0)
            spinnerOrderType.setSelection(options.orderBy?.ordinal?.plus(1)?:0)
        }
    }

    //TODO Is it possible the viewmodel is still null by these lifecycle calls if the activity is closed early enough?
    override fun onPause() {
        super.onPause()
        viewModel.updateHelper()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.updateHelper()
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                //TODO This looks really messy, consider DataBinding and replacing those enums
                binding.buttonApplyFilter -> {
                    with(binding) {
                        val currOptions = viewModel.getOptions()
                        val list = textinputCuisines.text?.toString()?.split(",")?.toList()
                        val commaSepList = list?.map { str -> str.toInt() }
                        val newOptions = RestaurantSearchFilterOptions(
                                currOptions.locationID,
                                currOptions.locationType,
                                textinputStartIndex.text?.toString()?.toIntOrNull(),
                                textinputMaxCount.text?.toString()?.toIntOrNull(),
                                textinputLatitude.text?.toString()?.toDoubleOrNull(),
                                textinputLongitude.text?.toString()?.toDoubleOrNull(),
                                textinputRadius.text?.toString()?.toDoubleOrNull(),
                                if(commaSepList != null) CommaSepList(commaSepList) else null,
                                textinputEstablishmentType.text?.toString(),
                                textinputCategory.text?.toString(),
                                textinputCollectionId.text?.toString(),
                                when(spinnerSortType.selectedItemPosition) {0 -> null
                                    1 -> Constants.Companion.SortType.COST 2 -> Constants.Companion.SortType.RATING
                                    3->Constants.Companion.SortType.REAL_DISTANCE else -> null},
                                when(spinnerOrderType.selectedItemPosition) { 0 -> null
                                    1 -> Constants.Companion.OrderType.ASCENDING
                                    2 -> Constants.Companion.OrderType.DESCENDING
                                    else -> null
                                }
                        )
                        viewModel.setOptions(newOptions)
                        Toast.makeText(this@FilterRestaurantSearchActivity, "Filters Applied", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}