package com.premonadeveloper.terasol.interfaces

import com.tag.crytohive.main.model.SymbolData
import com.tag.crytohive.main.utility.UrlConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers(UrlConstants.API_HEADER)
    @GET(UrlConstants.API_SYMBOL)
    fun getSymbols(): Call<SymbolData>

}