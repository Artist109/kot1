package com.example.myapplicationkotlin.api

import com.example.myapplicationkotlin.pojo.CoinInfoListOfData
import com.example.myapplicationkotlin.pojo.CoinPriceInfoRawData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = QUERY_PARAM_API_VALUE,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TSYM) tSym: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = QUERY_PARAM_API_VALUE,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): Single<CoinPriceInfoRawData>


    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_API_VALUE = "15c516aa60cdf97764f9a417ae8025806b37087b6f9cda431b704c6dfdbc89ef"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TSYM = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val CURRENCY = "USD"
    }
}