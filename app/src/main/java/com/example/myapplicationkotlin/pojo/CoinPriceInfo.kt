package com.example.myapplicationkotlin.pojo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplicationkotlin.api.ApiFactory.BASE_IMAGE_URL
import com.example.myapplicationkotlin.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity("full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    val type: String?,
    @SerializedName("MARKET")
    @Expose
    val market: String?,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,
    @SerializedName("FLAGS")
    @Expose
    val flags: String?,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,
    @SerializedName("MEDIAN")
    @Expose
    val median: Double?,
    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val topTierVolume24Hour: Double?,
    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    val topTierVolume24HourTo: Double?,
    @SerializedName("LASTTRADEID")
    @Expose
    val lastTradeId: String?,
    @SerializedName("PRICE")
    @Expose
    val price: Double?,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,
    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: Double?,
    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastVolumeTo: Double?,
    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumeHour: Double?,
    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumeHourTo: Double?,
    @SerializedName("OPENHOUR")
    @Expose
    val openHour: Double?,
    @SerializedName("HIGHHOUR")
    @Expose
    val highHour: Double?,
    @SerializedName("LOWHOUR")
    @Expose
    val lowHour: Double?,
    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: Double?,
    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumeDayTo: Double?,
    @SerializedName("OPENDAY")
    @Expose
    val openDay: Double?,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double?,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double?,
    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24Hour: Double?,
    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24HourTo: Double?,
    @SerializedName("OPEN24HOUR")
    @Expose
    val open24Hour: Double?,
    @SerializedName("HIGH24HOUR")
    @Expose
    val high24Hour: Double?,
    @SerializedName("LOW24HOUR")
    @Expose
    val low24Hour: Double?,
    @SerializedName("CHANGE24HOUR")
    @Expose
    val change24Hour: Double?,
    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    val changePct24Hour: Double?,
    @SerializedName("CHANGEDAY")
    @Expose
    val changeDay: Double?,
    @SerializedName("CHANGEPCTDAY")
    @Expose
    val changePctDay: Double?,
    @SerializedName("CHANGEHOUR")
    @Expose
    val changeHour: Double?,
    @SerializedName("CHANGEPCTHOUR")
    @Expose
    val changePctHour: Double?,
    @SerializedName("CONVERSIONTYPE")
    @Expose
    val conversionType: String?,
    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    val conversionSymbol: String?,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?
) {
    fun getFormattedTime():String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl():String {
        return BASE_IMAGE_URL + imageUrl
    }
}
