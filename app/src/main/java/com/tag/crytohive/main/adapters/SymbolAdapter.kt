package com.tag.crytohive.main.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.tag.crytohive.R
import com.tag.crytohive.main.activities.SymbolDetailActivity
import com.tag.crytohive.main.app.TagHiveApp
import com.tag.crytohive.main.model.SymbolData
import com.tag.crytohive.main.model.SymbolDataItem
import com.tag.crytohive.main.utility.Constants

class SymbolAdapter(private var symbolList: SymbolData, context: Context) :
    RecyclerView.Adapter<SymbolAdapter.SymbolViewHolder>() {
    var mContext = context


    inner class SymbolViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSymbolName: AppCompatTextView = view.findViewById(R.id.tvSymbolName)
        var ivInfo: AppCompatImageView = view.findViewById(R.id.ivSymbolDetail)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymbolViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_symbols, parent, false)
        return SymbolViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SymbolViewHolder, position: Int) {
        holder.tvSymbolName.text=symbolList.get(position).symbol
        holder.ivInfo.setOnClickListener {
            startDetailActivity(symbolList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return symbolList.size
    }



    fun startDetailActivity(symbols: SymbolDataItem) {
        val intent = Intent(mContext, SymbolDetailActivity::class.java)
        val data = TagHiveApp.getGsonInstance().toJson(symbols)
        intent.putExtra(Constants.EXTRA_KEY_SYMBOL_ITEM_DATA, data)
        mContext.startActivity(intent)
    }
}