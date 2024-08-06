package com.example.myapplicationkotlin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationkotlin.databinding.ItemCoinInfoBinding
import com.example.myapplicationkotlin.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoHolder>() {
    class CoinInfoHolder(var binding: ItemCoinInfoBinding) : RecyclerView.ViewHolder(binding.root)

    var coinInfoList = listOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoHolder {
        val binding =
            ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.coinInfoList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: CoinInfoHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder.binding) {
            val symbolsText = context.getString(
                com.example.myapplicationkotlin.R.string.symbols,
                coin.fromSymbol,
                coin.toSymbol
            )
            val lastUpdText = context.getString(
                com.example.myapplicationkotlin.R.string.last_updated_price,
                coin.getFormattedTime()
            )

            textViewSymbols.text = symbolsText
            textViewPrice.text = coin.price.toString()
            textViewUpdateTime.text = lastUpdText
            Picasso.get().load(coin.getFullImageUrl()).into(imageViewLogoCoin)
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}