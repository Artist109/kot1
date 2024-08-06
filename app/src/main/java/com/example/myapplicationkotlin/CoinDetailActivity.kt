package com.example.myapplicationkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LOG_TAG
import com.example.myapplicationkotlin.adapters.CoinInfoAdapter
import com.example.myapplicationkotlin.databinding.ActivityCoinDetailBinding
import com.example.myapplicationkotlin.databinding.ActivityCoinPriceListBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_FSYM)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FSYM)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        fromSymbol?.let {
            viewModel.getDetailInfo(it).observe(this, Observer {
                with(binding) {
                    textViewFromSymbol.text = it.fromSymbol
                    textViewToSymbol.text = it.toSymbol.toString()
                    textViewPrice.text = it.price.toString()
                    textViewMinPrice.text = it.lowDay.toString()
                    textViewMaxPrice.text = it.highDay.toString()
                    textViewLastOrder.text = it.lastMarket.toString()
                    textViewLastUpdate.text = it.getFormattedTime()
                    Picasso.get().load(it.getFullImageUrl()).into(imageViewLogoCoin)
                }
            })
        }
    }

    companion object {
        private const val EXTRA_FSYM = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FSYM, fromSymbol)
            return intent
        }
    }
}
