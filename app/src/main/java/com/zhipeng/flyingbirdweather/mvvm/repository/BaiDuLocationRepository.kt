package com.zhipeng.flyingbirdweather.mvvm.repository

import androidx.annotation.WorkerThread
import com.zhipeng.flyingbirdweather.mvvm.datasource.NetworkLocationQueryBaiDuMapDataSource

class BaiDuLocationRepository() {

    private val locationQueryBaiDuMapDataSource = NetworkLocationQueryBaiDuMapDataSource()

    @WorkerThread
    fun getLocationList(query: String, region: String): NetworkLocationQueryBaiDuMapDataSource.LocationResultModel? {
        return locationQueryBaiDuMapDataSource.queryLocation(query, region)
    }

}