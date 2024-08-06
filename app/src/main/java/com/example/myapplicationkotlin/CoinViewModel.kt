package com.example.myapplicationkotlin

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplicationkotlin.api.ApiFactory
import com.example.myapplicationkotlin.database.AppDatabase
import com.example.myapplicationkotlin.pojo.CoinPriceInfo
import com.example.myapplicationkotlin.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym:String):LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .map {
                it.data?.map {
                    it.coinInfo?.name
                }?.joinToString(",").toString()
            }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map {getPriceListFromRawData(it)}
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                Log.d("CoinViewsModel", "Success: $it")
            }, { it.message?.let { it1 -> Log.d("CoinViewsModel", "Error: $it1") } })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
        )
            : List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (key in currencyKeySet) {
                val priceInfo =
                    Gson().fromJson(currencyJson.getAsJsonObject(key), CoinPriceInfo::class.java)
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}