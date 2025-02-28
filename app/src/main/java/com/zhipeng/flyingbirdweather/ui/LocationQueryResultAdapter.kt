package com.zhipeng.flyingbirdweather.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhipeng.flyingbirdweather.R

class LocationQueryResultAdapter : RecyclerView.Adapter<LocationQueryResultAdapter.ViewHolder>() {

    private var locationList: List<LocationBean> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<LocationBean>) {
        locationList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_location_query_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(locationList[position])
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val city: TextView = view.findViewById(R.id.city)
        private val address: TextView = view.findViewById(R.id.address)

        fun bindData(bean: LocationBean) {
            name.text = bean.name
            val cityString = "省：${bean.province} 市：${bean.city} 区/县：${bean.district}"
            city.text = cityString
            address.text = bean.address
        }
    }

}