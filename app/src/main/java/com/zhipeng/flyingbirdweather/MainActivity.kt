package com.zhipeng.flyingbirdweather

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhipeng.flyingbirdweather.mvvm.viewmodel.MainActivityViewModel
import com.zhipeng.flyingbirdweather.ui.LocationBean
import com.zhipeng.flyingbirdweather.ui.LocationQueryResultAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class) }
    private lateinit var editText: AppCompatEditText
    private lateinit var locationQueryResultAdapter: LocationQueryResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationQueryResultAdapter = LocationQueryResultAdapter()
        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = locationQueryResultAdapter
        }
        editText = findViewById(R.id.edit)
        findViewById<View>(R.id.button).setOnClickListener {
            val text = editText.text?.toString() ?: ""
            if (text.isNotEmpty()) {
                viewModel.getLocation(text, text)
            }
        }
        viewModel.locationListLiveData.observe(this) {
            refreshRecyclerView(it)
        }
    }

    private fun refreshRecyclerView(list: List<LocationBean>) {
        locationQueryResultAdapter.setData(list)
    }

}