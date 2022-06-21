package com.tag.crytohive.main.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tag.crytohive.R
import com.tag.crytohive.databinding.ActivitySymbolDetailBinding
import com.tag.crytohive.main.app.TagHiveApp
import com.tag.crytohive.main.model.SymbolDataItem
import com.tag.crytohive.main.utility.Constants

class SymbolDetailActivity : AppCompatActivity() {
    lateinit var detailBinding: ActivitySymbolDetailBinding
    lateinit var symbolItem: SymbolDataItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivitySymbolDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
    }

    override fun onResume() {
        super.onResume()
        getSymbolItem()
    }

    private fun getSymbolItem() {
        val symbolDataJson=intent.getStringExtra(Constants.EXTRA_KEY_SYMBOL_ITEM_DATA)
        symbolItem = TagHiveApp.getGsonInstance()
            .fromJson(symbolDataJson, SymbolDataItem::class.java)
        setSymbolData(symbolItem)
    }

    @SuppressLint("SetTextI18n")
    private fun setSymbolData(symbolDataItem: SymbolDataItem) {
        detailBinding.tvOpenPrice.text = getString(R.string.open_price)+" - "+symbolDataItem.openPrice
        detailBinding.tvLowPriceValue.text = symbolDataItem.lowPrice
        detailBinding.tvLastPriceValue.text = symbolDataItem.lastPrice
        detailBinding.tvHighPriceValue.text = symbolDataItem.highPrice
        detailBinding.tvBaseAssetValue.text = symbolDataItem.baseAsset
        detailBinding.tvQuoteAssetValue.text = symbolDataItem.quoteAsset
        detailBinding.tvVolumeValue.text = symbolDataItem.volume
        detailBinding.tvSymboleValue.text = symbolDataItem.symbol
        detailBinding.tvBidPriceValue.text = symbolDataItem.bidPrice
        detailBinding.tvAskedValue.text = symbolDataItem.askPrice
    }
}