package com.tag.crytohive.main.app

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.premonadeveloper.terasol.ServiceGenerator.ServiceGenerator
import com.premonadeveloper.terasol.interfaces.ApiInterface
import com.tag.crytohive.main.model.SymbolData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityVM : ViewModel(), LifecycleObserver {
    var stringMutableLiveData: MutableLiveData<SymbolData>? = null

    fun getData(): LiveData<SymbolData?>? {
        if (stringMutableLiveData == null) {
            stringMutableLiveData = MutableLiveData<SymbolData>()
            loadData()
        }
        return stringMutableLiveData
    }


    fun loadData() {
        val apiService =
            ServiceGenerator.getClient()?.create(ApiInterface::class.java)

        val call = apiService?.getSymbols()
        call?.enqueue(object : Callback<SymbolData> {
            override fun onResponse(
                call: Call<SymbolData>,
                response: Response<SymbolData>
            ) {
                if (response.isSuccessful) {
                    stringMutableLiveData?.setValue(response.body())
                }else{
                    Log.d("Amit","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<SymbolData>, t: Throwable) {
                //DO NOTHING AS OF NOW
                Log.d("Amit","${t.stackTrace}")
            }

        })
    }
}
