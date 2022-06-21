package com.tag.crytohive.main.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tag.crytohive.R
import com.tag.crytohive.databinding.ActivityMainBinding
import com.tag.crytohive.main.adapters.SymbolAdapter
import com.tag.crytohive.main.model.SymbolData
import com.tag.crytohive.main.utility.NetWorkUtility

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainActivityVM
    private var symbolData = ArrayList<SymbolData>()
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        callApi()
    }

    fun callApi() {
        mainViewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        if (NetWorkUtility.isNetworkAvailable(this)) {
            mainViewModel.getData()?.observe(this, { symbols ->
                Log.d("Amit", "$symbols")
                symbols?.let { showSymbolData(it) }
            })
        } else {
            NetWorkUtility.generalInfoDia(
                this,
                getString(R.string.no_internet_title),
                getString(R.string.no_internet_sub)
            )
            mainBinding.progressBar.visibility = View.GONE
        }
    }

    fun showSymbolData(symbols: SymbolData) {
        mainBinding.rvSymbol.setHasFixedSize(true)
        mainBinding.rvSymbol.setLayoutManager(LinearLayoutManager(this))
        mainBinding.rvSymbol.setAdapter(SymbolAdapter(symbols, this))
        mainBinding.progressBar.visibility = View.GONE

    }
}