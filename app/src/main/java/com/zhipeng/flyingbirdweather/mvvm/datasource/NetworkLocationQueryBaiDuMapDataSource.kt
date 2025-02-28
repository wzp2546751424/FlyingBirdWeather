package com.zhipeng.flyingbirdweather.mvvm.datasource

import com.zhipeng.flyingbirdweather.mvvm.util.BAIDU_MAP_WEB_API_KY
import com.zhipeng.flyingbirdweather.mvvm.util.OkHttpManager
import com.zhipeng.flyingbirdweather.util.LogUtil
import okio.IOException
import org.json.JSONException
import org.json.JSONObject


class NetworkLocationQueryBaiDuMapDataSource {

    fun queryLocation(query: String, region: String): LocationResultModel? {
        var jsonString = ""
        return try {
            jsonString = OkHttpManager.get(getQueryUrl(query, region))
            LocationResultModel.fromJson(JSONObject(jsonString))
        } catch (exception: IOException) {
            LogUtil.e(javaClass.simpleName, "queryLocation: OkHttpManager.get(getQueryUrl(query, region)) error")
            null
        } catch (exception: JSONException) {
            LogUtil.e(javaClass.simpleName, "queryLocation: OkHttpManager.get(getQueryUrl(query, region)) error")
            null
        }
    }

    private fun getQueryUrl(query: String, region: String): String {
        return "https://api.map.baidu.com/place/v2/suggestion?query=$query&region=$region&city_limit=true&output=json&ak=$BAIDU_MAP_WEB_API_KY"
    }

    data class LocationResultModel(val status: Int, val message: String, val locationList: List<LocationModel>) {

        fun statusSuccess() = STATUS_SUCCESS == status

        companion object {

            @JvmStatic
            val STATUS_SUCCESS = 0

            @JvmStatic
            fun fromJson(jsonObject: JSONObject): LocationResultModel {
                val status = jsonObject.getInt("status")
                val message = jsonObject.optString("message")
                val locationList = if (status == STATUS_SUCCESS) {
                    val jsonArray = jsonObject.getJSONArray("result")
                    MutableList(jsonArray.length()) {
                        LocationModel.fromJson(jsonArray.getJSONObject(it))
                    }
                } else {
                    emptyList()
                }
                return LocationResultModel(status, message, locationList)
            }

        }

    }
    
    data class LocationModel(
        val name: String,
        val location: Location,
        val uid: String,
        val province: String,
        val city: String,
        val cityid: Int,
        val district: String,
        val adcode: Int,
        val business: String,
        val address: String
    ) {

        companion object {

            @JvmStatic
            fun fromJson(jsonObject: JSONObject): LocationModel {
                val name = jsonObject.getString("name")
                val location = Location.fromJson(jsonObject.getJSONObject("location"))
                val uid = jsonObject.getString("uid")

                val province = jsonObject.optString("province")
                val city = jsonObject.optString("city")
                val cityid = jsonObject.optInt("cityid")
                val district = jsonObject.optString("district")
                val adcode = jsonObject.optInt("adcode")
                val business = jsonObject.optString("business")
                val address = jsonObject.optString("address")
                return LocationModel(name, location, uid, province, city, cityid, district, adcode, business, address)
            }

        }

    }

    data class Location(val lat: Float, val lng: Float) {

        companion object {

            @JvmStatic
            fun fromJson(jsonObject: JSONObject): Location {
                val lat = jsonObject.getDouble("lat")
                val lng = jsonObject.getDouble("lng")
                return Location(lat.toFloat(), lng.toFloat())
            }

        }

    }

}