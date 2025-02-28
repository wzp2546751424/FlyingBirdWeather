package com.zhipeng.flyingbirdweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherApplication : Application() {

    @SuppressLint("StaticFieldLeak")
    companion object {

        @JvmStatic
        lateinit var context: Context

        @JvmStatic
        val caiYunToken = "oon8rVay4hjtv5Oj"

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}