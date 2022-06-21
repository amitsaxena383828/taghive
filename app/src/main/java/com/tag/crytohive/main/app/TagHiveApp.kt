package com.tag.crytohive.main.app

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TagHiveApp: Application() {

    companion object {
        private lateinit var gson: Gson
        private lateinit var instance: TagHiveApp
        @JvmStatic
        fun getInstance() = instance
        @JvmStatic
        fun getGsonInstance() = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}