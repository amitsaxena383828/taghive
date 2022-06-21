package com.premonadeveloper.terasol.ServiceGenerator

import com.google.gson.GsonBuilder
import com.tag.crytohive.main.utility.UrlConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private var retrofit: Retrofit? = null
    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(UrlConstants.BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}