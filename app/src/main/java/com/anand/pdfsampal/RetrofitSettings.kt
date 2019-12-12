package com.anand.pdfsampal

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by anand
 */

object RetrofitSettings {

    //This method establish the OkHtttpClient timers
    private fun client(): OkHttpClient {
        //Return the OkHttpClient with the read and connect Timeouts
        return OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    //This method helps us creating the RetrofitServie
    /*
     * serviceClass = The class that provides the endpoints that the service will have
     * bseUrl = The url that will be called to request the endpoints
     * */
    fun <T> createRetrofitService(serviceClass: Class<T>, baseUrl: String): T {
        //Generate retrofit setting the baseUrl, client (OkHttp) and converterFactory (Gson)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client())
            .build()

        //Return the retrofit service generate from retrofit settings
        return retrofit.create(serviceClass)
    }
}