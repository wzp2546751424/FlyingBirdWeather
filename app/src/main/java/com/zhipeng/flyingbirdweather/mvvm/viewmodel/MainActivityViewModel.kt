package com.zhipeng.flyingbirdweather.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhipeng.flyingbirdweather.mvvm.datasource.NetworkLocationQueryBaiDuMapDataSource
import com.zhipeng.flyingbirdweather.mvvm.repository.BaiDuLocationRepository
import com.zhipeng.flyingbirdweather.ui.LocationBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val locationRepository: BaiDuLocationRepository by lazy { BaiDuLocationRepository() }

    val locationListLiveData get() = _locationListLiveData as LiveData<List<LocationBean>>
    private val _locationListLiveData = MutableLiveData<List<LocationBean>>()

    fun getLocation(query: String, region: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val model = locationRepository.getLocationList(query, region)
            val list = if (model.isValid()) {
                model!!.locationList.map {
                    LocationBean(it.name, it.location, it.uid, it.province, it.city, it.cityid, it.district, it.address)
                }
            } else {
                emptyList()
            }
            _locationListLiveData.postValue(list)
        }
    }

}

fun NetworkLocationQueryBaiDuMapDataSource.LocationResultModel?.isValid() = this != null && this.statusSuccess()