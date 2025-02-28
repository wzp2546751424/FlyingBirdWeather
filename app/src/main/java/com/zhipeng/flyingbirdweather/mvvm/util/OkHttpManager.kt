package com.zhipeng.flyingbirdweather.mvvm.util

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object OkHttpManager {

    private val client by lazy { OkHttpClient() }

    @Throws(IOException::class)
    fun get(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response.body!!.string()
        }
    }

}