package com.zhipeng.flyingbirdweather.ui

import com.zhipeng.flyingbirdweather.mvvm.datasource.NetworkLocationQueryBaiDuMapDataSource.Location

data class LocationBean(
    val name: String,
    val location: Location,
    val uid: String,
    val province: String,
    val city: String,
    val cityid: Int,
    val district: String,
    val address: String
)