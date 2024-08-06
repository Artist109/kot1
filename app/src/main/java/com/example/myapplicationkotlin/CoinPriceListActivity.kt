package com.example.myapplicationkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationkotlin.adapters.CoinInfoAdapter
import com.example.myapplicationkotlin.databinding.ActivityCoinPriceListBinding
import com.example.myapplicationkotlin.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coinInfoAdapter = CoinInfoAdapter(this)
        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {

                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }

        with(binding.recycleViewCoinPriceList) {
            adapter = coinInfoAdapter
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            coinInfoAdapter.coinInfoList = it
        })
    }
}