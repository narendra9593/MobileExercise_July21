package com.mobileexercise.api

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger.Companion
import okio.IOException


class ApiCall {
    private var client = OkHttpClient()
    private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    @Throws(IOException::class)
    fun postRequest(url: String?, json: String?): String {
        Log.d("ApiCall", "postRequest: $url  $json")
        val body: RequestBody = json.toString().toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(url.toString())
            .post(body)
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }

    @Throws(IOException::class)
    fun getRequest(url: String?): String? {
        Log.d("ApiCall", "getRequest: $url")
        val request: Request = Request.Builder()
            .url(url.toString())
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .build()
        client.newCall(request).execute().use { response -> return response.body?.string() }
    }
}


